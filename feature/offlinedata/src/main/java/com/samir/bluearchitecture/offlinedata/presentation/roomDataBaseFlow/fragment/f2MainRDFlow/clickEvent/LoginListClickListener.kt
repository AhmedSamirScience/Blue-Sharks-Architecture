package com.samir.bluearchitecture.offlinedata.presentation.roomDataBaseFlow.fragment.f2MainRDFlow.clickEvent

import com.samir.bluearchitecture.offlinedata.data.dataSource.model.LoginEntity

interface LoginListClickListener {
  fun onItemClicked(itemSelected: LoginEntity)
}
