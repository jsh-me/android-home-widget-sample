package com.jshme.covidwidget.domain.usecase

import com.jshme.covidwidget.domain.repository.CovidRepository
import javax.inject.Inject

class GetCountryCovidCount @Inject constructor(
    private val repository: CovidRepository
): BaseUseCase {
    suspend operator fun invoke() = repository.getCountryCovidCounter()
}
