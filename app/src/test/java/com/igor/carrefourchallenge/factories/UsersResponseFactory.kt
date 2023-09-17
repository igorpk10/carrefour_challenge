package com.igor.carrefourchallenge.factories

import com.igor.carrefourchallenge.data.models.UsersResponse


fun buildUserResponseList() = listOf<UsersResponse>(
    UsersResponse(
        login = "String",
        id = 0,
        nodeId = "String",
        avatarUrl = "String",
        gravatarId = "String",
        url = "String",
        htmlUrl = "String",
        followersUrl = "String",
        followingUrl = "String",
        gistsUrl = "String",
        starredUrl = "String",
        subscriptionsUrl = "String",
        organizationsUrl = "String",
        reposUrl = "String",
        eventsUrl = "String",
        receivedEventsUrl = "String",
        type = "String",
        siteAdmin = false
    ),
    UsersResponse(
        login = "String2",
        id = 0,
        nodeId = "String",
        avatarUrl = "String",
        gravatarId = "String",
        url = "String",
        htmlUrl = "String",
        followersUrl = "String",
        followingUrl = "String",
        gistsUrl = "String",
        starredUrl = "String",
        subscriptionsUrl = "String",
        organizationsUrl = "String",
        reposUrl = "String",
        eventsUrl = "String",
        receivedEventsUrl = "String",
        type = "String",
        siteAdmin = false
    )
)