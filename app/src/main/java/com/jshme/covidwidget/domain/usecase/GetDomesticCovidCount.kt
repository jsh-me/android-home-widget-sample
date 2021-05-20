package com.jshme.covidwidget.domain.usecase

import com.jshme.covidwidget.domain.repository.CovidRepository
import javax.inject.Inject

class GetDomesticCovidCount @Inject constructor(
    private val repository: CovidRepository
): BaseUseCase {
    suspend operator fun invoke() = repository.getDomesticCovidCounter()
}
