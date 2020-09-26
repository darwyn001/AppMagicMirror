package com.lemusc.magicmirrorcontroller.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lemusc.magicmirrorcontroller.R
import com.lemusc.magicmirrorcontroller.poko.Module
import kotlinx.android.synthetic.main.rv_module_item.view.*

class ModulesAdapter(
    val clickListener: (Module) -> Unit
) : RecyclerView.Adapter<ModulesAdapter.ViewHolder>() {

    private val itemList: MutableList<Module> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_module_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val module = itemList[position]

        holder.tvId.text = module.id
        holder.tvName.text = module.nombre
        holder.tvshow.text = if(module.mostrar) "si" else "no"

        holder.itemView.setOnClickListener { clickListener(module) }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItems(list: List<Module>) {
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearList() {
        itemList.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId = view.textViewId
        val tvName = view.textViewName
        val tvshow = view.textViewShow
    }
}