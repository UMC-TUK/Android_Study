package com.example.chapter9

data class Data(
    val cur_sts: String,
    val delay_rsn: Any,
    val delay_sec: Int,
    val dest_st_cd: String,
    val dest_st_nm: String,
    val loc_cur_cd: String,
    val loc_cur_nm: String,
    val loc_plan_cd: String,
    val loc_plan_nm: String,
    val train_code: String,
    val train_dir: String,
    val train_state: String
)