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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModulesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_module_item, parent, false))
    }

    override fun onBindViewHolder(holder: ModulesAdapter.ViewHolder, position: Int) {
        val module = itemList[position]

        holder.tvPosition.text = module.index
        holder.tvId.text = module.id
        holder.tvName.text = module.nombre
        holder.tvPath.text = module.ruta
        holder.tvFile.text = module.archivo
        holder.tvClase.text = module.clase
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
        val tvPosition = view.textViewPosition
        val tvId = view.textViewId
        val tvName = view.textViewName
        val tvPath = view.textViewPath
        val tvFile = view.textViewFile
        val tvClase = view.textViewClass
        val tvshow = view.textViewShow
    }
}