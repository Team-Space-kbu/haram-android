package com.space.data.service.intranet

import com.space.shared.SpaceBody
import com.space.shared.model.IntranetModel

interface IntranetService {
    fun setIntranetId(intranetModel: IntranetModel): SpaceBody<String>
}