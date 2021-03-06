package com.ssafy.aongbucks_user.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.aongbucks_user.model.dto.Order
import com.ssafy.aongbucks_user.model.dto.OrderDetail
import com.ssafy.aongbucks_user.model.dto.ShoppingCart
import com.ssafy.aongbucks_user.model.dto.TotalCart

class MainActivityViewModel: ViewModel() {

    /**
     * NFC
     */

    var nfcFlag = false
        private set

    fun changeNfcFlag(flag: Boolean) {
        nfcFlag = flag
    }


    /**
     * Gift Card Money
     */

    private val _cardMoney = MutableLiveData<Int>()
    val cardMoney : LiveData<Int>
        get() = _cardMoney

    fun readGiftCard(price: String) {
        _cardMoney.value = price.toInt()
    }


    /**
     * aong pay
     */
    var currentPayMoney: Int = 0
        private set

    fun initPayMoney(price: Int) {
        currentPayMoney = price
    }

    /**
     * Membership
     */
    var discountByGrade: Float = 0.0f
        private set

    fun changeDiscount(discount: Float) {
        discountByGrade = discount
    }

    /**
     * Shopping Cart
     */
    // 최종 쇼핑 카트 (-> 주문 예정)
    var finalShoppingCart: Order? = null
        private set

    // 전체 금액 저장
    var totalCart : TotalCart? = null
        private set

    private fun makeTotalCart(price: Int, cnt: Int) {
        val mile:Float = 0.1f // 10% 적립

        totalCart = TotalCart(
            price, (price*discountByGrade/100).toInt(), (price*mile).toInt(), cnt
        )
    }

    // 주문 번호
    var orderId: Int = -1
        private set

    fun completeOrder(id: Int) {
        orderId = id
    }

    // 장바구니
    var shoppingCart = mutableListOf<ShoppingCart>()
        private set

    fun addCart(cart: ShoppingCart) {
        val index = getIndex(cart.menuId)

        if (index != -1) {
            // 기존에 중복되는 menu가 있으면, 개수 추가해주기
            shoppingCart[index].addDupMenu(cart)
        } else {
            // 새로 추가
            shoppingCart.add(cart)
        }
    }

    fun removeCart(position: Int) {
        shoppingCart.removeAt(position)
    }

    /**
     * @return 중복되는 id의 index / 없으면 -1
     */
    fun getIndex(menuId: Int): Int {
        val size = shoppingCart.size

        for (i in 0 until size) {
            if (menuId == shoppingCart[i].menuId) return i // 중복
        }

        return -1 // 중복X
    }

    fun getTotalPrice(): Int {
        var price = 0
        var cnt = 0

        val size = shoppingCart.size

        for (i in 0 until size) {
            price += shoppingCart[i].totalPrice
            cnt += shoppingCart[i].menuCnt
        }

        makeTotalCart(price, cnt) // 세부 결제 금액 정보 갱신

        return price
    }

    fun getCartQuantitySize(): Int {
        var total = 0

        val size = shoppingCart.size

        for (i in 0 until size) {
            total += shoppingCart[i].menuCnt
        }

        return total
    }

    fun setFinalCart(order: Order) {
        val size = shoppingCart.size

        order.apply {
            totalQuantity = getCartQuantitySize()
            totalPrice = getTotalPrice()
            topProductName = shoppingCart[0].menuName
            topImg = shoppingCart[0].menuImg

            // detail
            for (i in 0 until size) {
                details.add(OrderDetail(order.id, shoppingCart[i].menuId, shoppingCart[i].menuCnt))
            }
        }

        finalShoppingCart = order
    }
}