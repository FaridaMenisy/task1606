package com.example.myapplication.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myapplication.TestCoroutineRule
import com.example.myapplication.data.nw.Result
import com.example.myapplication.data.source.remote.GetChartsRemoteDataSource
import com.example.myapplication.domain.model.ChartsDataModel
import com.example.myapplication.domain.model.mapper.ChartResponseMapper
import com.example.myapplication.domain.repository.GetChartsRepo
import com.example.myapplication.test_utils.ChartsTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response


class GetChartsRepoImplTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var getChartsRemoteDataSource: GetChartsRemoteDataSource

    @Mock
    private lateinit var chartsObserver: Observer<Result<ChartsDataModel>>

    @Mock
    private lateinit var chartResponseMapper: ChartResponseMapper

    private lateinit var getChartsRepo: GetChartsRepo


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getChartsRepo = GetChartsRepoImpl(getChartsRemoteDataSource, chartResponseMapper).apply {
            this.chartsLiveData.observeForever(chartsObserver)
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `fetchChartsData-Success+Nw-getData`() {
        testCoroutineRule.runBlockingTest {

            //arrange
            whenever(getChartsRemoteDataSource.getChartsData(any(), any(), any())).thenReturn(
                ChartsTestUtil.getChartsSuccessRawResponse()
            )


            //act
            getChartsRepo.getChartsRepo("", "", "")

            //assert
            verify(chartsObserver).onChanged(Result.Loading)
            verify(chartsObserver).onChanged(Result.Success(ChartsTestUtil.getChartsDomainResponse()))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchChartsData-Fails-fails`() {
        testCoroutineRule.runBlockingTest {

            //arrange
            whenever(getChartsRemoteDataSource.getChartsData(any(), any(), any())).thenReturn(
                Response.error(404, "".toResponseBody(null))
            )
            //act
            getChartsRepo.getChartsRepo("", "", "")

            //assert
            verify(chartsObserver).onChanged(Result.Loading)

        }
    }
}