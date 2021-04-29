package com.ayo.doggallery.utils

import com.ayo.api.exceptions.NoNetworkException
import com.ayo.api.exceptions.ServerException
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApiErrorTest {


    @Test
    fun `test no network exception`() {

        val result = ApiError.extractErrorMessage(NoNetworkException())

        assertSame("Please connect to the internet", result)

    }


    @Test
    fun `test server exception`() {

        val result = ApiError.extractErrorMessage(ServerException(0, ""))
        assertSame("Dogs API is currently down", result)

    }

    @Test
    fun `test other exceptions`() {

        val result = ApiError.extractErrorMessage(null)

        assertSame("Problem fetching dogs", result)

    }
}