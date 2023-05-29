package com.example.chapter9_exercise.coronamodel

data class Result(
    val cnt_confirmations: String,
    val cnt_deaths: String,
    val cnt_hospitalizations: String,
    val cnt_severe_symptoms: String,
    val mmddhh: String,
    val rate_confirmations: String,
    val rate_deaths: String,
    val rate_hospitalizations: String,
    val rate_severe_symptoms: String
)