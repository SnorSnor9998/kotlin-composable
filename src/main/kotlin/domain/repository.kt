// READ ME FIRST!
//
// Code in this file is shared between the Android and Desktop JVM targets.
// Kotlin's hierarchical multiplatform projects currently
// don't support sharing code depending on JVM declarations.
//
// You can follow the progress for HMPP JVM & Android intermediate source sets here:
// https://youtrack.jetbrains.com/issue/KT-42466
//
// Because of the workaround used, some tooling might not behave as expected.
//
// Resolution errors (expect/actual, red code) in your IDE
// do not indicate a problem with your setup.


package domain.repositories

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.CustomTypeAdapter
import com.apollographql.apollo.api.CustomTypeValue
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.pos.mutation.*
import com.pos.query.FindFacilitiesQuery
import com.pos.query.GetBookingQuery
import com.pos.query.GetFacilityQuery
import com.pos.query.GetProfileQuery
import com.pos.type.*
import okhttp3.OkHttpClient
import java.time.Instant
import java.util.*

private fun decode(input: String) = input.toCharArray().map { it + 1 }.joinToString("")

val defaultAuth = decode("/`4/81b6db605e8d6``bdc7ecba8d2/a7/370`20")

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

//enum class IssuesState(val title: String, val githubState: IssueState) {
//    OPEN("Open", IssueState.OPEN),
//    CLOSED("Closed", IssueState.CLOSED)
//}

//data class Issues(
//    val nodes: List<IssuesQuery.Node>,
//    val cursor: String?,
//    val state: IssuesState,
//    val order: OrderDirection
//)

//interface IssuesRepository {
//    fun getFacities()
//    fun getIssues(state: IssuesState, order: OrderDirection, cursor: String? = null, callback: (Result<Issues>) -> Unit)
//    fun getIssue(id: Int, callback: (Result<IssueQuery.Issue>) -> Unit)
//}

class UnknownRepo : RuntimeException()
class UnknownIssue : RuntimeException()

private const val baseUrl = "https://sb-graph.wetix.my/query"

class RepositoryImpl(
    val token: String
) {

    private val client: ApolloClient by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "bearer $token")
                    .build()

                chain.proceed(request)
            }
            .build()

        val dateTypeAdapter = object : CustomTypeAdapter<Date> {
            override fun encode(value: Date): CustomTypeValue<*> {
                throw UnsupportedOperationException()
            }

            override fun decode(value: CustomTypeValue<*>): Date {
                val v = value.value
                if (v is String) {
                    return Date.from(Instant.parse(v))
                }
                throw IllegalArgumentException(value.toString())
            }
        }

        ApolloClient.builder()
            .serverUrl(baseUrl)
            .addCustomTypeAdapter(CustomType.DATETIME, dateTypeAdapter)
            .addCustomTypeAdapter(CustomType.DATE, dateTypeAdapter)
            .okHttpClient(okHttpClient)
            .build()
    }

     fun findFacilities(
         filter: Input<FilterFacilities>,
         date: Date,
         callback: (Result<List<FindFacilitiesQuery.Facility>>) -> Unit) {
        val query = FindFacilitiesQuery(filter, date)
        client.query(query).enqueue(
            object : ApolloCall.Callback<FindFacilitiesQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    callback(Result.Error(e))
                }
                override fun onResponse(response: Response<FindFacilitiesQuery.Data>) {
                    val facilities = response.data?.facilities
                    response.data?.facilities!!.map {
                        println(it)
                    }

                    if (facilities == null) {
                        callback(Result.Error(UnknownRepo()))
                    } else {
                        try {
                            callback(Result.Success(facilities))
                        } catch (e: NullPointerException) {
                            callback(Result.Error(e))
                        }
                    }
                }
            }
        )
    }

     fun getProfile(
        callback: (Result<GetProfileQuery.Profile>) -> Unit) {
        val query = GetProfileQuery()
        client.query(query).enqueue(
            object : ApolloCall.Callback<GetProfileQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    callback(Result.Error(e))
                }
                override fun onResponse(response: Response<GetProfileQuery.Data>) {
                    val profile = response.data?.profile
                    println(profile)

                    if (profile == null) {
                        callback(Result.Error(UnknownRepo()))
                    } else {
                        try {
                            callback(Result.Success(profile))
                        } catch (e: NullPointerException) {
                            callback(Result.Error(e))
                        }
                    }
                }
            }
        )
    }

    fun getFacility(
        key: String,
        date: Date,
        callback: (Result<GetFacilityQuery.Facility>) -> Unit) {
        val query = GetFacilityQuery(key, date)
        client.query(query).enqueue(object : ApolloCall.Callback<GetFacilityQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                callback(Result.Error(e))
            }

            override fun onResponse(response: Response<GetFacilityQuery.Data>) {
                val facility = response.data?.facility
                if (facility == null) {
                    callback(Result.Error(UnknownIssue()))
                } else {
                    callback(Result.Success(facility))
                }
            }
        })
    }

    fun getBooking(
        id: String,
        callback: (Result<GetBookingQuery.Booking>) -> Unit) {
        val query = GetBookingQuery(id)
        client.query(query).enqueue(object : ApolloCall.Callback<GetBookingQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                callback(Result.Error(e))
            }

            override fun onResponse(response: Response<GetBookingQuery.Data>) {
                val booking = response.data?.booking
                if (booking == null) {
                    callback(Result.Error(UnknownIssue()))
                } else {
                    callback(Result.Success(booking))
                }
            }
        })
    }

    fun updateProfile(
        input: UpdateProfile,
        callback: (Result<UpdateProfileMutation.UpdateProfile>) -> Unit) {
        val query = UpdateProfileMutation(input)
        client.mutate(query).enqueue(object : ApolloCall.Callback<UpdateProfileMutation.Data>() {
            override fun onFailure(e: ApolloException) {
                callback(Result.Error(e))
            }

            override fun onResponse(response: Response<UpdateProfileMutation.Data>) {
                val result = response.data?.updateProfile
                if (result == null) {
                    callback(Result.Error(UnknownIssue()))
                } else {
                    callback(Result.Success(result))
                }
            }
        })
    }

    fun createFacility(
        input: CreateFacility,
        callback: (Result<CreateFacilityMutation.CreateFacility>) -> Unit) {
        val query = CreateFacilityMutation(input)
        client.mutate(query).enqueue(object : ApolloCall.Callback<CreateFacilityMutation.Data>() {
            override fun onFailure(e: ApolloException) {
                callback(Result.Error(e))
            }

            override fun onResponse(response: Response<CreateFacilityMutation.Data>) {
                val result = response.data?.createFacility
                if (result == null) {
                    callback(Result.Error(UnknownIssue()))
                } else {
                    callback(Result.Success(result))
                }
            }
        })
    }

    fun updateFacility(
        key: String,
        input: UpdateFacility,
        callback: (Result<UpdateFacilityMutation.UpdateFacility>) -> Unit) {
        val query = UpdateFacilityMutation(key, input)
        client.mutate(query).enqueue(object : ApolloCall.Callback<UpdateFacilityMutation.Data>() {
            override fun onFailure(e: ApolloException) {
                callback(Result.Error(e))
            }

            override fun onResponse(response: Response<UpdateFacilityMutation.Data>) {
                val result = response.data?.updateFacility
                if (result == null) {
                    callback(Result.Error(UnknownIssue()))
                } else {
                    callback(Result.Success(result))
                }
            }
        })
    }

    fun disableFacility(
        key: String,
        callback: (Result<DisableFacilityMutation.DisableFacility>) -> Unit) {
        val query = DisableFacilityMutation(key)
        client.mutate(query).enqueue(object : ApolloCall.Callback<DisableFacilityMutation.Data>() {
            override fun onFailure(e: ApolloException) {
                callback(Result.Error(e))
            }

            override fun onResponse(response: Response<DisableFacilityMutation.Data>) {
                val result = response.data?.disableFacility
                if (result == null) {
                    callback(Result.Error(UnknownIssue()))
                } else {
                    callback(Result.Success(result))
                }
            }
        })
    }

    fun createPlaytime(
        key: String,
        input: CreatePlaytime,
        callback: (Result<CreatePlaytimeMutation.CreatePlaytime>) -> Unit) {
        val query = CreatePlaytimeMutation(key, input)
        client.mutate(query).enqueue(object : ApolloCall.Callback<CreatePlaytimeMutation.Data>() {
            override fun onFailure(e: ApolloException) {
                callback(Result.Error(e))
            }

            override fun onResponse(response: Response<CreatePlaytimeMutation.Data>) {
                val result = response.data?.createPlaytime
                if (result == null) {
                    callback(Result.Error(UnknownIssue()))
                } else {
                    callback(Result.Success(result))
                }
            }
        })
    }

    fun disablePlaytimes(
        input: DisablePlaytimes,
        callback: (Result<Boolean>) -> Unit) {
        val query = DisablePlaytimesMutation(input)
        client.mutate(query).enqueue(object : ApolloCall.Callback<DisablePlaytimesMutation.Data>() {
            override fun onFailure(e: ApolloException) {
                callback(Result.Error(e))
            }

            override fun onResponse(response: Response<DisablePlaytimesMutation.Data>) {
                val result = response.data?.disablePlaytimes
                if (result == null) {
                    callback(Result.Error(UnknownIssue()))
                } else {
                    callback(Result.Success(result))
                }
            }
        })
    }

    fun createBooking(
        input: CreateBooking,
        callback: (Result<CreateBookingMutation.CreateBooking>) -> Unit) {
        val query = CreateBookingMutation(input)
        client.mutate(query).enqueue(object : ApolloCall.Callback<CreateBookingMutation.Data>() {
            override fun onFailure(e: ApolloException) {
                callback(Result.Error(e))
            }

            override fun onResponse(response: Response<CreateBookingMutation.Data>) {
                val result = response.data?.createBooking
                if (result == null) {
                    callback(Result.Error(UnknownIssue()))
                } else {
                    callback(Result.Success(result))
                }
            }
        })
    }

    fun refundBooking(
        key: String,
        callback: (Result<RefundBookingMutation.RefundBooking>) -> Unit) {
        val query = RefundBookingMutation(key)
        client.mutate(query).enqueue(object : ApolloCall.Callback<RefundBookingMutation.Data>() {
            override fun onFailure(e: ApolloException) {
                callback(Result.Error(e))
            }

            override fun onResponse(response: Response<RefundBookingMutation.Data>) {
                val result = response.data?.refundBooking
                if (result == null) {
                    callback(Result.Error(UnknownIssue()))
                } else {
                    callback(Result.Success(result))
                }
            }
        })
    }
}