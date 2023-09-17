package com.igor.carrefourchallenge.factories

import com.igor.carrefourchallenge.data.models.UserResponse


fun buildUserResponse() = UserResponse(
    login = "login",
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
    siteAdmin = false,
    name = "String",
    company = "String",
    blog = "String",
    location = "String",
    email = "String",
    hireable = "String",
    bio = "String",
    twitterUsername = "String",
    publicRepos = 0,
    publicGists = 0,
    followers = 0,
    following = 0,
    createdAt = "String",
    updatedAt = "String"
)