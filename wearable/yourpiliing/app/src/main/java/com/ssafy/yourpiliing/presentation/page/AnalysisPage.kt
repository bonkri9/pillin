package com.ssafy.yourpiliing.presentation.page

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ssafy.yourpiliing.presentation.component.AnalysisRadarChart
import com.ssafy.yourpiliing.presentation.retrofit.analysis.AnalysisState
import com.ssafy.yourpiliing.presentation.retrofit.analysis.ExtractionAnalysisResponse
import com.ssafy.yourpiliing.presentation.viewmodel.AnalysisViewModel

@Composable
fun AnalysisPage(analysisViewModel: AnalysisViewModel) {
    val sharedPreferences = LocalContext.current.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val analysisState by analysisViewModel.analysisState.collectAsState(AnalysisState.Loading)

    LaunchedEffect(Unit) {
        analysisViewModel.analysis(sharedPreferences)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (analysisState) {
            is AnalysisState.Loading -> {
                // TODO: 로딩 메시지 출력
            }

            is AnalysisState.Success -> {
                val datas =
                    (analysisState as AnalysisState.Success).response.essentialNutrientsDataList

                // 추출 완료
                val extractionAnalysisResponse = ExtractionAnalysisResponse(datas)
                AnalysisRadarChart(
                    radarLabels = extractionAnalysisResponse.nutrients,
                    excessiveTakes = extractionAnalysisResponse.excessiveTakes,
                    userTakes = extractionAnalysisResponse.userTakes,
                    recommendedIntakes = extractionAnalysisResponse.recommendedIntakes
                )
            }

            is AnalysisState.Failure -> {
                // TODO: 실패 메시지 출력
            }

            else -> {}
        }
    }


}