package com.space.shared.model

data class ReservationsModel(
    var userName: String,
    val phoneNum: String,
    val calendarSeq: Int,
    val reservationPolicyRequests: List<PolicyReqModel>,
    val timeRequests: List<TimeReqModel>,
)

data class PolicyReqModel(
    val policySeq: Int,
    val policyAgreeYn: String
)

data class TimeReqModel(
    val timeSeq: Int
)
