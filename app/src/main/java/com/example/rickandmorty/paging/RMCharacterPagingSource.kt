package com.example.rickandmorty.paging
import RmCharacter
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.servicies.ApiInterface
import retrofit2.HttpException
import java.io.IOException

class RMCharacterPagingSource(
    private val api: ApiInterface
) : PagingSource<Int, RmCharacter>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RmCharacter> {
        val page = params.key ?: 1
        return try {
            val response = api.getCharacters(page)
            val characters = response.results

            val nextPage = if (response.info?.next != null) page + 1 else null

            LoadResult.Page(
                data = characters,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextPage
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, RmCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}