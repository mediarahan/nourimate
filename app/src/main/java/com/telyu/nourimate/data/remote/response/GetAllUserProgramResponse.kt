package com.telyu.nourimate.data.remote.response

data class GetAllUserProgramResponse(
    val message: String,
    val programs: List<UserProgram>
)

data class UserProgram(
    val program_id: Int,
    val ongoingProgram: Int,
    val startDate: String,
    val endDate: String,
    val startWeight: Int,
    val endWeight: Int,
    val editCurrentWeightDate: String,
    val user_id: Int
)
