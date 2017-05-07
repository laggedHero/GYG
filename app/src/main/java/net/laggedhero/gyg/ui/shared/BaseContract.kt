package net.laggedhero.gyg.ui.shared

class BaseContract {

    interface View

    interface Presenter<in T : View> {

        fun bindView(view: T)

        fun release()
    }
}
