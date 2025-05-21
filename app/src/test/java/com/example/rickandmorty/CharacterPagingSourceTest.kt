package com.example.rickandmorty

import Characters
import Info
import RmCharacter
import androidx.paging.PagingSource
import com.example.rickandmorty.paging.RMCharacterPagingSource
import com.example.rickandmorty.servicies.ApiInterface
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException

class CharacterPagingSourceTest {

    private lateinit var api: ApiInterface
    private lateinit var pagingSource: RMCharacterPagingSource

    @Before
    fun setUp() {
        api = mockk()
        pagingSource = RMCharacterPagingSource(api)
    }

    @Test
    fun `load returns Page on success`() = runTest {
        val characters = listOf(
            RmCharacter(
                id = 1,
                name = "Rick",
                species = "Human",
                type = "",
                gender = "Male",
                image = "https://example.com/image.png"
            )
        )
        val response = Characters(
            info = Info(
                count = 1,
                pages = 1,
                next = "some_url",
                prev = null
            ),
            results = characters
        )

        coEvery { api.getCharacters(1) } returns response

        val params = PagingSource.LoadParams.Refresh(
            key = 1,
            loadSize = 20,
            placeholdersEnabled = false
        )

        val expected = PagingSource.LoadResult.Page(
            data = characters,
            prevKey = null,
            nextKey = 2
        )

        val result = pagingSource.load(params)

        Assert.assertEquals(expected, result)
    }

    @Test
    fun `load returns Error on IOException`() = runTest {
        coEvery { api.getCharacters(any()) } throws IOException()

        val params = PagingSource.LoadParams.Refresh(
            key = 1,
            loadSize = 20,
            placeholdersEnabled = false
        )

        val result = pagingSource.load(params)

        Assert.assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun `load returns Error on HttpException`() = runTest {
        val httpException = mockk<HttpException>()
        coEvery { api.getCharacters(any()) } throws httpException

        val params = PagingSource.LoadParams.Refresh(
            key = 1,
            loadSize = 10,
            placeholdersEnabled = false
        )

        val result = pagingSource.load(params)

        Assert.assertTrue(result is PagingSource.LoadResult.Error)
    }
}