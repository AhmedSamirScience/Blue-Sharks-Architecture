package com.samir.bluearchitecture.offlinedata.presentation.roomDataBaseFlow.fragment.f2MainRDFlow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity
import com.samir.bluearchitecture.offlinedata.databinding.ItemRecordsBinding
import com.samir.bluearchitecture.offlinedata.presentation.roomDataBaseFlow.fragment.f2MainRDFlow.clickEvent.LoginListClickListener

class LoginListAdapter(private var originalList: List<LoginEntity> = listOf()) : RecyclerView.Adapter<LoginListAdapter.ViewHolder>() {

  private var filteredList: MutableList<LoginEntity> = originalList.toMutableList()
  var itemClickListener: LoginListClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val itemBinding: ItemRecordsBinding = ItemRecordsBinding.inflate(layoutInflater, parent, false)
    return ViewHolder(itemBinding)
  }

  override fun getItemCount(): Int {
    return filteredList.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(filteredList[position])
  }

  fun submitMyList(newList: List<LoginEntity>, clickListener: LoginListClickListener) {
    originalList = newList
    filteredList = newList.toMutableList()
    itemClickListener = clickListener
    notifyDataSetChanged()
  }
  inner class ViewHolder(private val bindingItem: ItemRecordsBinding) : RecyclerView.ViewHolder(bindingItem.root) {

    fun bind(item: LoginEntity) {
      bindingItem.myItem = item
    }
  }
}
