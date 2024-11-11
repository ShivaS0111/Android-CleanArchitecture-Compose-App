package com.invia.data.fake

import com.google.gson.Gson
import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie

data class FakeResp(val data:List<Movie>)
class FakeData {

    suspend fun getDataSourceTvShows(): Result<List<Movie>>{
        val da = Gson().fromJson(data, FakeResp::class.java)
        return Result.Success(da.data)
    }


    private val data = "{\"data\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"url\": \"https://www.tvmaze.com/shows/1/under-the-dome\",\n" +
            "      \"name\": \"Under the Dome\",\n" +
            "      \"type\": \"Scripted\",\n" +
            "      \"language\": \"English\",\n" +
            "      \"genres\": [\n" +
            "        \"Drama\",\n" +
            "        \"Science-Fiction\",\n" +
            "        \"Thriller\"\n" +
            "      ],\n" +
            "      \"status\": \"Ended\",\n" +
            "      \"runtime\": 60,\n" +
            "      \"averageRuntime\": 60,\n" +
            "      \"premiered\": \"2013-06-24\",\n" +
            "      \"ended\": \"2015-09-10\",\n" +
            "      \"officialSite\": \"http://www.cbs.com/shows/under-the-dome/\",\n" +
            "      \"schedule\": {\n" +
            "        \"time\": \"22:00\",\n" +
            "        \"days\": [\n" +
            "          \"Thursday\"\n" +
            "        ]\n" +
            "      },\n" +
            "      \"rating\": {\n" +
            "        \"average\": 6.5\n" +
            "      },\n" +
            "      \"weight\": 97,\n" +
            "      \"network\": {\n" +
            "        \"id\": 2,\n" +
            "        \"name\": \"CBS\",\n" +
            "        \"country\": {\n" +
            "          \"name\": \"United States\",\n" +
            "          \"code\": \"US\",\n" +
            "          \"timezone\": \"America/New_York\"\n" +
            "        },\n" +
            "        \"officialSite\": \"https://www.cbs.com/\"\n" +
            "      },\n" +
            "      \"webChannel\": null,\n" +
            "      \"dvdCountry\": null,\n" +
            "      \"externals\": {\n" +
            "        \"tvrage\": 25988,\n" +
            "        \"thetvdb\": 264492,\n" +
            "        \"imdb\": \"tt1553656\"\n" +
            "      },\n" +
            "      \"image\": {\n" +
            "        \"medium\": \"https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg\",\n" +
            "        \"original\": \"https://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg\"\n" +
            "      },\n" +
            "      \"summary\": \"<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>\",\n" +
            "      \"updated\": 1704794065,\n" +
            "      \"_links\": {\n" +
            "        \"self\": {\n" +
            "          \"href\": \"https://api.tvmaze.com/shows/1\"\n" +
            "        },\n" +
            "        \"previousepisode\": {\n" +
            "          \"href\": \"https://api.tvmaze.com/episodes/185054\",\n" +
            "          \"name\": \"The Enemy Within\"\n" +
            "        }\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"url\": \"https://www.tvmaze.com/shows/2/person-of-interest\",\n" +
            "      \"name\": \"Person of Interest\",\n" +
            "      \"type\": \"Scripted\",\n" +
            "      \"language\": \"English\",\n" +
            "      \"genres\": [\n" +
            "        \"Action\",\n" +
            "        \"Crime\",\n" +
            "        \"Science-Fiction\"\n" +
            "      ],\n" +
            "      \"status\": \"Ended\",\n" +
            "      \"runtime\": 60,\n" +
            "      \"averageRuntime\": 60,\n" +
            "      \"premiered\": \"2011-09-22\",\n" +
            "      \"ended\": \"2016-06-21\",\n" +
            "      \"officialSite\": \"http://www.cbs.com/shows/person_of_interest/\",\n" +
            "      \"schedule\": {\n" +
            "        \"time\": \"22:00\",\n" +
            "        \"days\": [\n" +
            "          \"Tuesday\"\n" +
            "        ]\n" +
            "      },\n" +
            "      \"rating\": {\n" +
            "        \"average\": 8.8\n" +
            "      },\n" +
            "      \"weight\": 98,\n" +
            "      \"network\": {\n" +
            "        \"id\": 2,\n" +
            "        \"name\": \"CBS\",\n" +
            "        \"country\": {\n" +
            "          \"name\": \"United States\",\n" +
            "          \"code\": \"US\",\n" +
            "          \"timezone\": \"America/New_York\"\n" +
            "        },\n" +
            "        \"officialSite\": \"https://www.cbs.com/\"\n" +
            "      },\n" +
            "      \"webChannel\": null,\n" +
            "      \"dvdCountry\": null,\n" +
            "      \"externals\": {\n" +
            "        \"tvrage\": 28376,\n" +
            "        \"thetvdb\": 248742,\n" +
            "        \"imdb\": \"tt1839578\"\n" +
            "      },\n" +
            "      \"image\": {\n" +
            "        \"medium\": \"https://static.tvmaze.com/uploads/images/medium_portrait/163/407679.jpg\",\n" +
            "        \"original\": \"https://static.tvmaze.com/uploads/images/original_untouched/163/407679.jpg\"\n" +
            "      },\n" +
            "      \"summary\": \"<p>You are being watched. The government has a secret system, a machine that spies on you every hour of every day. I know because I built it. I designed the Machine to detect acts of terror but it sees everything. Violent crimes involving ordinary people. People like you. Crimes the government considered \\\"irrelevant\\\". They wouldn't act so I decided I would. But I needed a partner. Someone with the skills to intervene. Hunted by the authorities, we work in secret. You'll never find us. But victim or perpetrator, if your number is up, we'll find you.</p>\",\n" +
            "      \"updated\": 1717084401,\n" +
            "      \"_links\": {\n" +
            "        \"self\": {\n" +
            "          \"href\": \"https://api.tvmaze.com/shows/2\"\n" +
            "        },\n" +
            "        \"previousepisode\": {\n" +
            "          \"href\": \"https://api.tvmaze.com/episodes/659372\",\n" +
            "          \"name\": \"return 0\"\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  ]}"

}