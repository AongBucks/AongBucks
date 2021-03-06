package com.ssafy.aongbucks_user.fragment

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.activity.MainActivity
import com.ssafy.aongbucks_user.adapter.CommentAdapter
import com.ssafy.aongbucks_user.config.ApplicationClass
import com.ssafy.aongbucks_user.databinding.FragmentMenuDetailBinding
import com.ssafy.aongbucks_user.model.dto.Comment
import com.ssafy.aongbucks_user.model.dto.ShoppingCart
import com.ssafy.aongbucks_user.model.dto.User
import com.ssafy.aongbucks_user.model.response.MenuDetailWithCommentResponse
import com.ssafy.aongbucks_user.util.CommonUtils
import com.ssafy.aongbucks_user.viewModel.CommentViewModel
import com.ssafy.aongbucks_user.viewModel.FavoriteViewModel
import com.ssafy.aongbucks_user.viewModel.MainActivityViewModel
import com.ssafy.aongbucks_user.viewModel.ProductViewModel
import kotlinx.android.synthetic.main.list_item_comment.view.*

private const val TAG = "MenuDetailFragment_싸피"
class MenuDetailFragment : Fragment() {
    private lateinit var binding : FragmentMenuDetailBinding
    private lateinit var mainActivity: MainActivity

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private val pViewModel : ProductViewModel by viewModels()
    private val cViewModel : CommentViewModel by viewModels()
    private val fViewModel : FavoriteViewModel by viewModels()

    private lateinit var commentAdapter : CommentAdapter

    private lateinit var user : User
    private var productId = 0
    private var isFavorite = 0
    private var menuCnt = 1
    private var menuPrice = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        mainActivity.hideBottomNav(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity.hideBottomNav(true)
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

        Log.d(TAG, "onViewCreated: $productId")
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

        binding.addCartBtn.setOnClickListener {
            if (menuCnt == 0) {
                Toast.makeText(context,"상품 개수를 추가해주세요.",Toast.LENGTH_SHORT).show()
            } else {
                addCart()
                Toast.makeText(context,"상품이 장바구니에 담겼습니다.",Toast.LENGTH_SHORT).show()
            }
        }

        binding.favoriteBtn.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                if (isChecked && isFavorite == 0) { // 즐겨찾기에 추가
                    fViewModel.addFavorite(user.id, productId)
                    fViewModel.added.observe(viewLifecycleOwner, { added ->
                        if (added) {
                            binding.favoriteBtn.isChecked = true
                            isFavorite = 1
                            Toast.makeText(requireContext(), R.string.favorite_added, Toast.LENGTH_SHORT).show()
                        }
                    })
                } else if (!isChecked && isFavorite != 0) { // 즐겨찾기에서 제거
                    fViewModel.delFavorite(user.id, productId)
                    fViewModel.deleted.observe(viewLifecycleOwner, { deleted ->
                        if (deleted) {
                            binding.favoriteBtn.isChecked = false
                            isFavorite = 0
                            Toast.makeText(requireContext(), R.string.favorite_deleted, Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        })
    }
    
    private fun getData() {
        pViewModel.getProductWithComments(productId, user.id)
        pViewModel.productWithComments.observe(viewLifecycleOwner, {
            val menuWithComments = pViewModel.productWithComments.value as List<MenuDetailWithCommentResponse>
            val menu = menuWithComments[0]
            menuPrice = menu.productPrice
            Log.d(TAG, "onViewCreated menuDetail: $menu")
            isFavorite = menu.isFavorite

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
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
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
        }
    }

    private fun addCart() {
        val m = binding.menu
        if (m != null) {
            val shoppingCart = ShoppingCart(productId, m.productImg,
                m.productName, menuCnt, menuPrice,
                menuCnt*menuPrice, m.type)

            activityViewModel.addCart(shoppingCart)

            Log.d(TAG, "addCart: 현재 카트상황 ${activityViewModel.shoppingCart}")
        }
    }
}