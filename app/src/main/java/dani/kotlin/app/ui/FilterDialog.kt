package dani.kotlin.app.ui

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import androidx.fragment.app.DialogFragment
import dani.kotlin.app.R
import dani.kotlin.app.ui.listener.DialogListener

class FilterDialog : DialogFragment()  {

    private lateinit var listener: DialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(
                R.layout.filter_by_dialog,
                null)

            val filters  = arrayListOf<String>()
            val title    = view.findViewById<TextView>(R.id.filterTitle)
            val shopping = view.findViewById<CheckBox>(R.id.filterShopping)
            val food     = view.findViewById<CheckBox>(R.id.filterFood)
            val beauty   = view.findViewById<CheckBox>(R.id.filterBeauty)
            val leisure  = view.findViewById<CheckBox>(R.id.filterLeisure)
            val button   = view.findViewById<Button>(R.id.filterResults)

            if (arguments != null) {
                shopping.isChecked = isFilterChecked("shopping")
                food.isChecked     = isFilterChecked("food")
                beauty.isChecked   = isFilterChecked("beauty")
                leisure.isChecked  = isFilterChecked("leisure")
            }

            title.text = getFormattedTitle()
            button.setOnClickListener {
                if (shopping.isChecked) filters.add(getString(R.string.shopping_tag))
                if (food.isChecked)     filters.add(getString(R.string.food_tag))
                if (beauty.isChecked)   filters.add(getString(R.string.beauty_tag))
                if (leisure.isChecked)  filters.add(getString(R.string.leisure_tag))

                listener.onDialogClosed(filters)
                dismiss()
            }

            builder.setView(view)
            builder.create()

        } ?: throw IllegalStateException(
            getString(R.string.illegal_exception))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(getString(
                R.string.must_implement_dialog,
                context.toString()))
        }
    }

    private fun isFilterChecked(key: String): Boolean
        = arguments?.getString(key) != null

    private fun getFormattedTitle(): Spanned {
        return if (Build.VERSION.SDK_INT >= 24)
            Html.fromHtml(getString(R.string.filter_by_title),
                HtmlCompat.FROM_HTML_MODE_LEGACY)
        else
            Html.fromHtml(getString(R.string.filter_by_title))
    }
}