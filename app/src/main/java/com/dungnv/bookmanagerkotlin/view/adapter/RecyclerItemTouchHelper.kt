package com.dungnv.bookmanagerkotlin.view.adapter

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View

/**
 * Created by ravi on 29/09/17.
 *@ItemTouchHelper.SimpleCallback
 *
 * Tạo ra một cuộc gọi lại cho phép kéo và trượt cho phép.
 * Các giá trị này đóng vai trò là mặc định và nếu bạn muốn tùy chỉnh hành vi cho mỗi ViewHolder,
 * bạn có thể ghi đè
 *
 * > getDefaultUIUtil () sẽ được ItemTouchHelper sử dụng để phát hiện bất cứ khi nào có thay đổi giao diện người dùng trên chế độ xem.
 * Chúng tôi sử dụng chức năng này để giữ chế độ xem nền ở vị trí tĩnh và di chuyển chế độ xem nền trước.
 *
 */

class RecyclerItemTouchHelper(dragDirs: Int,
                              swipeDirs: Int,
                              private val listener: RecyclerItemTouchHelperListener)
    : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    val TAG = "RecyclerItemTouchHelper"

    override fun onMove(p0: RecyclerView,
                        p1: RecyclerView.ViewHolder,
                        p2: RecyclerView.ViewHolder): Boolean {
        Log.d(TAG, "onMove......")
        return true
    }//1


    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?,
                                   actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (viewHolder != null) {
            val foregroundView = (viewHolder as UserAdapter.ViewHolder).viewForeground
            ItemTouchHelper.Callback.getDefaultUIUtil().onSelected(foregroundView)
            Log.d(TAG, "onSelectedChanged  viewHolder != null......")

        } else {

            Log.d(TAG, "onSelectedChanged viewHolder == null......")
        }


    }


    override fun clearView(recyclerView: RecyclerView,
                           viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)

        val foregroundView: View = (viewHolder as UserAdapter.ViewHolder).viewForeground
        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(foregroundView)
        Log.d(TAG, "clearView......")


    }

    override fun onChildDraw(c: Canvas,
                             recyclerView: RecyclerView,
                             viewHolder: RecyclerView.ViewHolder,
                             dX: Float,
                             dY: Float,
                             actionState: Int,
                             isCurrentlyActive: Boolean) {

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val foregroundView: View = (viewHolder as UserAdapter.ViewHolder).viewForeground

        ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c,
                recyclerView,
                foregroundView,
                dX,
                dY,
                actionState,
                isCurrentlyActive)
        Log.d(TAG, "onChildDraw......")

    }

    /**
     * > Trong onChildDrawOver () vị trí x của giao diện tiền cảnh được thay đổi trong khi người dùng đang vuốt chế độ xem.
     */
    override fun onChildDrawOver(c: Canvas,
                                 recyclerView: RecyclerView,
                                 viewHolder: RecyclerView.ViewHolder,
                                 dX: Float,
                                 dY: Float,
                                 actionState: Int,
                                 isCurrentlyActive: Boolean) {

        val foregroundView = (viewHolder as UserAdapter.ViewHolder).viewForeground

        ItemTouchHelper.Callback.getDefaultUIUtil().onDrawOver(c,
                recyclerView,
                foregroundView,
                dX,
                dY,
                actionState,
                isCurrentlyActive)
        Log.d(TAG, "onChildDrawOver......")

    }//2


    override fun onSwiped(p0: RecyclerView.ViewHolder,
                          p1: Int) {
        listener.onSwiped(p0, p1, p0.adapterPosition)

        Log.d(TAG, "onSwiped......")

    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        return super.convertToAbsoluteDirection(flags, layoutDirection)
        Log.d(TAG, "convertToAbsoluteDirection......")
    }

    //interface

    /**
     * > RecyclerItemTouchHelperListener giao diện được sử dụng để gửi gọi lại để thực hiện hoạt động.
     * Ở đây người nghe sẽ được kích hoạt trong MainActivity khi vuốt xong.
     */

    interface RecyclerItemTouchHelperListener {
        fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int)
    }
}