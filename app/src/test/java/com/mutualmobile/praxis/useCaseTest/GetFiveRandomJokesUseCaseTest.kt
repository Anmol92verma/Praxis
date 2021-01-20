package com.mutualmobile.praxis.useCaseTest

import com.mutualmobile.praxis.data.SafeResult
import com.mutualmobile.praxis.BaseTest
import com.mutualmobile.praxis.injection.component.TestAppComponent
import com.mutualmobile.praxis.domain.usecases.GetFiveRandomJokesUseCase
import com.mutualmobile.praxis.util.FileUtil
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import javax.inject.Inject

class GetFiveRandomJokesUseCaseTest : BaseTest() {

    override fun injectIntoDagger(testAppComponent: TestAppComponent) {
        testAppComponent.inject(this)
    }

    @Inject
    lateinit var getFiveRandomJokesUseCase: GetFiveRandomJokesUseCase

    @Test
    fun `when api returns success- assert result data contains Jokes`() =
            runBlocking {
                mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(FileUtil.loadText("responses/jokes_response.json")))

                val result = getFiveRandomJokesUseCase.perform()
                assertEquals(1, mockWebServer.requestCount)
                assert(result is SafeResult.Success)
                assert((result as SafeResult.Success).data.isNotEmpty())
            }

}