package dani.kotlin.app.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import dani.kotlin.app.R
import dani.kotlin.domain.entities.CommerceInfo
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dani.kotlin.app.CommerceViewModel
import dani.kotlin.app.ui.CommerceDetail

class CommerceAdapter(private val items: List<CommerceInfo>)
    : RecyclerView.Adapter<CommerceAdapter.CommerceViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int):
            CommerceViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.commerce_card, parent,
            false) as CardView

        return CommerceViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommerceViewHolder, position: Int) {
        val logo = items[position].logo?.thumbnails?.medium
        Picasso.get().load(logo).into(holder.commerceImage)

        holder.commerceName.text = items[position].name
        holder.commerceDesc.text = items[position].shortDescription
        holder.commerceLocation.text = items[position].address?.street

        holder.itemView.setOnClickListener {
            CommerceViewModel.detailCommerce.postValue(items[position])
            val intent = Intent(it.context, CommerceDetail::class.java)
            (it.context as Activity).startActivity(intent)
        }
    }

    override fun getItemCount() = items.size

    class CommerceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val commerceImage = view.findViewById(R.id.commerceImage)       as ImageView
        var commerceName  = view.findViewById(R.id.commerceName)        as TextView
        var commerceDesc  = view.findViewById(R.id.commerceDescription) as TextView
        var commerceLocation = view.findViewById(R.id.commerceLocation) as TextView
    }
}