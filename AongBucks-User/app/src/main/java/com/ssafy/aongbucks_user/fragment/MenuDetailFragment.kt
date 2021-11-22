package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.adapter.CommentAdapter
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.databinding.FragmentMenuDetailBinding
import com.ssafy.aongbucks_user.model.dto.Comment
import com.ssafy.aongbucks_user.model.dto.User
import com.ssafy.aongbucks_user.model.response.MenuDetailWithCommentResponse
import com.ssafy.aongbucks_user.util.CommonUtils
import com.ssafy.aongbucks_user.viewModel.CommentViewModel
import com.ssafy.aongbucks_user.viewModel.ProductViewModel
import kotlinx.android.synthetic.main.list_item_comment.view.*

private const val TAG = "MenuDetailFragment_싸피"
class MenuDetailFragment : Fragment() {
    private lateinit var binding : FragmentMenuDetailBinding
    private lateinit var mainActivity: MainActivity

    private val viewModel : ProductViewModel by viewModels()
    private val cViewModel : CommentViewModel by viewModels()

    private lateinit var commentAdapter : CommentAdapter

    private lateinit var user : User
    private var productId = 0
    private var menuCnt = 1
    private var menuPrice = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productId = arguments?.getInt("productId")!!
        user = ApplicationClass.sharedPreferencesUtil.getUser()

        getData()
        heartAnimation()
        initListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }

    private fun initListener() {
        binding.minusBtn.setOnClickListener {
            if (menuCnt > 1) {
                menuCnt--
                binding.menuCnt.text = menuCnt.toString()
                if (menuCnt == 1)
                    changeBtnColor(binding.minusBtn.drawable, R.color.light_gray)
            }
            binding.menuPrice.text = CommonUtils.makeComma(menuCnt * menuPrice)
        }

        binding.plusBtn.setOnClickListener {
            menuCnt++
            binding.menuCnt.text = menuCnt.toString()
            binding.menuPrice.text = CommonUtils.makeComma(menuCnt * menuPrice)
            changeBtnColor (binding.minusBtn.drawable, R.color.medium_gray)
        }

        binding.favoriteBtn.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                when (isChecked) {
                    true -> { // 즐겨찾기에 추가

                    }
                    false -> { // 즐겨찾기에서 제거

                    }
                }
            }
        })
    }
    
    private fun getData() {
        viewModel.getProductWithComments(productId, user.id)
        viewModel.productWithComments.observe(viewLifecycleOwner, {
            val menuWithComments = viewModel.productWithComments.value as List<MenuDetailWithCommentResponse>
            val menu = menuWithComments[0]
            menuPrice = menu.productPrice
            Log.d(TAG, "onViewCreated: $menu")

            binding.menu = menu
            if (menuWithComments[0].productCommentTotalCnt > 0) {
                setAdapter(menuWithComments)
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.noComments.visibility = View.VISIBLE
            }
        })
    }
    
    // 즐겨찾기 버튼 애니메이션
    private fun heartAnimation() {
        val bounceInterpolator = BounceInterpolator()
        val scaleAnimation = ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f,  Animation.RELATIVE_TO_SELF, 0.7f).apply { 
            duration = 500
            interpolator = bounceInterpolator
        }
        
        binding.favoriteBtn.setOnCheckedChangeListener { compoundButton, isChecked ->
            compoundButton.startAnimation(scaleAnimation)
        }
    }

    private fun changeBtnColor(img : Drawable, rId : Int) {
        val d = DrawableCompat.wrap(img)
        DrawableCompat.setTint(d.mutate(), ContextCompat.getColor(requireContext(), rId))
    }

    private fun setAdapter(comments : List<MenuDetailWithCommentResponse>) {
        commentAdapter = CommentAdapter(requireContext(), comments.toMutableList()).apply {
            val listener = object : CommentAdapter.ButtonClickListener {
                override fun onDelete(view: View, position: Int, commentId: Int) {
                    cViewModel.delComment(commentId)
                    cViewModel.deleted.observe(viewLifecycleOwner, { deleted ->
                        if (deleted) {
                            Toast.makeText(requireContext(), R.string.comment_deleted, Toast.LENGTH_SHORT).show()
                            commentList.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, itemCount)
                            if (itemCount == 0) {
                                binding.recyclerView.visibility = View.GONE
                                binding.noComments.visibility = View.VISIBLE
                            }
                        }
                    })
                }

                override fun onModify(view: View, position: Int, data: MenuDetailWithCommentResponse) {
                    val content = view.editComment.text.toString()
                    if (content.isEmpty()) {
                        Toast.makeText(requireContext(), R.string.comment_empty, Toast.LENGTH_SHORT).show()
                        return
                    }

                    val comment = Comment(data.commentId, data.userId!!, productId, data.productRating, content)
                    cViewModel.editComment(comment)
                    cViewModel.edited.observe(viewLifecycleOwner, { edited ->
                        if (edited) {
                            view.content.text = content
                            commentList[position].commentContent = content
                            Toast.makeText(requireContext(), R.string.comment_updated, Toast.LENGTH_SHORT).show()
                            notifyItemChanged(position)
                        }
                    })
                }
            }
            setBtnClickListener(listener)
        }
        initAdapter()
    }

    private fun initAdapter() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.noComments.visibility = View.GONE
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = commentAdapter
            adapter!!.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }
}