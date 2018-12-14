package com.example.target

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.example.target.data.User
import com.example.target.repository.AppRepository
import com.example.target.ui.trending.GithubTrendingFragmentViewModel
import com.google.gson.Gson
import io.reactivex.Observable
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class GithubTrendingFragmentViewModelTest {

    @Rule @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    private lateinit var githubTrendingFragmentViewModel: GithubTrendingFragmentViewModel

    @Mock
    private lateinit var observer: Observer<List<User>>

    @Mock
    private lateinit var appRepository: AppRepository

    private val mockedData: String = "[{\"username\":\"google\",\"name\":\"Google\",\"url\":\"https://github.com/google\",\"avatar\":\"https://avatars0.githubusercontent.com/u/1342004?s=96&v=4\",\"repo\":{\"name\":\"guava\",\"description\":\"Google core libraries for Java\",\"url\":\"https://github.com/google/guava\"}},{\"username\":\"Microsoft\",\"name\":\"Microsoft\",\"url\":\"https://github.com/Microsoft\",\"avatar\":\"https://avatars2.githubusercontent.com/u/6154722?s=96&v=4\",\"repo\":{\"name\":\"malmo\",\"description\":\"Project Malmo is a platform for Artificial Intelligence experimentation and research built on top of Minecraft. We aim to inspire a new generation of research into challenging new problems presented by this unique environment. --- For installation instructions, scroll down to *Getting Started* below, or visit the project page for more information:\",\"url\":\"https://github.com/Microsoft/malmo\"}},{\"username\":\"flutter\",\"name\":\"Flutter\",\"url\":\"https://github.com/flutter\",\"avatar\":\"https://avatars1.githubusercontent.com/u/14101776?s=96&v=4\",\"repo\":{\"name\":\"flutter-intellij\",\"description\":\"Flutter makes it easy and fast to build beautiful mobile apps.\",\"url\":\"https://github.com/flutter/flutter-intellij\"}},{\"username\":\"apache\",\"name\":\"The Apache Software Foundation\",\"url\":\"https://github.com/apache\",\"avatar\":\"https://avatars0.githubusercontent.com/u/47359?s=96&v=4\",\"repo\":{\"name\":\"incubator-dubbo\",\"description\":\"Apache Dubbo (incubating) is a high-performance, java based, open source RPC framework.\",\"url\":\"https://github.com/apache/incubator-dubbo\"}},{\"username\":\"facebook\",\"name\":\"Facebook\",\"url\":\"https://github.com/facebook\",\"avatar\":\"https://avatars2.githubusercontent.com/u/69631?s=96&v=4\",\"repo\":{\"name\":\"fresco\",\"description\":\"An Android library for managing images and the memory they use.\",\"url\":\"https://github.com/facebook/fresco\"}},{\"username\":\"alibaba\",\"name\":\"Alibaba\",\"url\":\"https://github.com/alibaba\",\"avatar\":\"https://avatars1.githubusercontent.com/u/1961952?s=96&v=4\",\"repo\":{\"name\":\"fastjson\",\"description\":\"\uD83D\uDE84 A fast JSON parser/generator for Java\",\"url\":\"https://github.com/alibaba/fastjson\"}},{\"username\":\"Snailclimb\",\"name\":\"SnailClimb\",\"url\":\"https://github.com/Snailclimb\",\"avatar\":\"https://avatars2.githubusercontent.com/u/29880145?s=96&v=4\",\"repo\":{\"name\":\"JavaGuide\",\"description\":\"【Java学习+面试指南】 一份涵盖大部分Java程序员所需要掌握的核心知识。\",\"url\":\"https://github.com/Snailclimb/JavaGuide\"}},{\"username\":\"fossasia\",\"name\":\"FOSSASIA\",\"url\":\"https://github.com/fossasia\",\"avatar\":\"https://avatars3.githubusercontent.com/u/6295529?s=96&v=4\",\"repo\":{\"name\":\"susi_server\",\"description\":\"SUSI.AI server backend - the Artificial Intelligence server for personal assistants\",\"url\":\"https://github.com/fossasia/susi_server\"}},{\"username\":\"Tencent\",\"name\":\"Tencent\",\"url\":\"https://github.com/Tencent\",\"avatar\":\"https://avatars0.githubusercontent.com/u/18461506?s=96&v=4\",\"repo\":{\"name\":\"tinker\",\"description\":\"Tinker is a hot-fix solution library for Android, it supports dex, library and resources update without reinstall apk.\",\"url\":\"https://github.com/Tencent/tinker\"}},{\"username\":\"qunarcorp\",\"name\":\"Qunar, Inc.\",\"url\":\"https://github.com/qunarcorp\",\"avatar\":\"https://avatars2.githubusercontent.com/u/43158242?s=96&v=4\",\"repo\":{\"name\":\"qmq\",\"description\":\"QMQ是去哪儿网内部广泛使用的消息中间件，自2012年诞生以来在去哪儿网所有业务场景中广泛的应用，包括跟交易息息相关的订单场景； 也包括报价搜索等高吞吐量场景。\",\"url\":\"https://github.com/qunarcorp/qmq\"}},{\"username\":\"aws\",\"name\":\"Amazon Web Services\",\"url\":\"https://github.com/aws\",\"avatar\":\"https://avatars2.githubusercontent.com/u/2232217?s=96&v=4\",\"repo\":{\"name\":\"aws-sdk-java\",\"description\":\"The official AWS SDK for Java.\",\"url\":\"https://github.com/aws/aws-sdk-java\"}},{\"username\":\"uber\",\"name\":\"Uber Open Source\",\"url\":\"https://github.com/uber\",\"avatar\":\"https://avatars2.githubusercontent.com/u/538264?s=96&v=4\",\"repo\":{\"name\":\"RIBs\",\"description\":\"Uber's cross-platform mobile architecture framework.\",\"url\":\"https://github.com/uber/RIBs\"}},{\"username\":\"mercyblitz\",\"name\":\"小马哥\",\"url\":\"https://github.com/mercyblitz\",\"avatar\":\"https://avatars2.githubusercontent.com/u/533114?s=96&v=4\",\"repo\":{\"name\":\"segmentfault-lessons\",\"description\":\"Segment Fault 在线讲堂 代码工程\",\"url\":\"https://github.com/mercyblitz/segmentfault-lessons\"}},{\"username\":\"SplashCodes\",\"url\":\"https://github.com/SplashCodes\",\"avatar\":\"https://avatars2.githubusercontent.com/u/19502618?s=96&v=4\",\"repo\":{\"name\":\"JAViewer\",\"description\":\"更优雅的驾车体验\",\"url\":\"https://github.com/SplashCodes/JAViewer\"}},{\"username\":\"spring-projects\",\"name\":\"Spring\",\"url\":\"https://github.com/spring-projects\",\"avatar\":\"https://avatars1.githubusercontent.com/u/317776?s=96&v=4\",\"repo\":{\"name\":\"spring-boot\",\"description\":\"Spring Boot\",\"url\":\"https://github.com/spring-projects/spring-boot\"}},{\"username\":\"airbnb\",\"name\":\"Airbnb\",\"url\":\"https://github.com/airbnb\",\"avatar\":\"https://avatars1.githubusercontent.com/u/698437?s=96&v=4\",\"repo\":{\"name\":\"lottie-android\",\"description\":\"Render After Effects animations natively on Android and iOS, Web, and React Native\",\"url\":\"https://github.com/airbnb/lottie-android\"}},{\"username\":\"macrozheng\",\"name\":\"macro\",\"url\":\"https://github.com/macrozheng\",\"avatar\":\"https://avatars3.githubusercontent.com/u/15903809?s=96&v=4\",\"repo\":{\"name\":\"mall\",\"description\":\"mall项目是一套电商系统，包括前台商城系统及后台管理系统，基于SpringBoot+MyBatis实现。 前台商城系统包含首页门户、商品推荐、商品搜索、商品展示、购物车、订单流程、会员中心、客户服务、帮助中心等模块。 后台管理系统包含商品管理、订单管理、会员管理、促销管理、运营管理、内容管理、统计报表、财务管理、权限管理、设置等模块。\",\"url\":\"https://github.com/macrozheng/mall\"}},{\"username\":\"TheAlgorithms\",\"name\":\"The Algorithms\",\"url\":\"https://github.com/TheAlgorithms\",\"avatar\":\"https://avatars3.githubusercontent.com/u/20487725?s=96&v=4\",\"repo\":{\"name\":\"Java\",\"description\":\"All Algorithms implemented in Java\",\"url\":\"https://github.com/TheAlgorithms/Java\"}},{\"username\":\"elastic\",\"name\":\"elastic\",\"url\":\"https://github.com/elastic\",\"avatar\":\"https://avatars1.githubusercontent.com/u/6764390?s=96&v=4\",\"repo\":{\"name\":\"elasticsearch\",\"description\":\"Open Source, Distributed, RESTful Search Engine\",\"url\":\"https://github.com/elastic/elasticsearch\"}},{\"username\":\"Ramotion\",\"name\":\"Ramotion\",\"url\":\"https://github.com/Ramotion\",\"avatar\":\"https://avatars1.githubusercontent.com/u/6028820?s=96&v=4\",\"repo\":{\"name\":\"folding-cell-android\",\"description\":\"\uD83D\uDCC3 FoldingCell is a material design expanding content cell inspired by folding paper material made by\",\"url\":\"https://github.com/Ramotion/folding-cell-android\"}},{\"username\":\"Qihoo360\",\"name\":\"Qihoo 360\",\"url\":\"https://github.com/Qihoo360\",\"avatar\":\"https://avatars2.githubusercontent.com/u/4082929?s=96&v=4\",\"repo\":{\"name\":\"RePlugin\",\"description\":\"RePlugin - A flexible, stable, easy-to-use Android Plug-in Framework\",\"url\":\"https://github.com/Qihoo360/RePlugin\"}},{\"username\":\"awslabs\",\"name\":\"Amazon Web Services - Labs\",\"url\":\"https://github.com/awslabs\",\"avatar\":\"https://avatars0.githubusercontent.com/u/3299148?s=96&v=4\",\"repo\":{\"name\":\"aws-sdk-android-samples\",\"description\":\"This repository has samples that demonstrate various aspects of the AWS Mobile SDK for Android, you can get the SDK source on Github\",\"url\":\"https://github.com/awslabs/aws-sdk-android-samples\"}},{\"username\":\"Azure\",\"name\":\"Microsoft Azure\",\"url\":\"https://github.com/Azure\",\"avatar\":\"https://avatars2.githubusercontent.com/u/6844498?s=96&v=4\",\"repo\":{\"name\":\"azure-sdk-for-java\",\"description\":\"Azure SDKs for Java\",\"url\":\"https://github.com/Azure/azure-sdk-for-java\"}},{\"username\":\"b3log\",\"name\":\"B3log 开源\",\"url\":\"https://github.com/b3log\",\"avatar\":\"https://avatars0.githubusercontent.com/u/1627618?s=96&v=4\",\"repo\":{\"name\":\"symphony\",\"description\":\"\uD83C\uDFB6 一款用 Java 实现的现代化社区（论坛/BBS/社交网络/博客）平台。\",\"url\":\"https://github.com/b3log/symphony\"}},{\"username\":\"googlesamples\",\"name\":\"Google Samples\",\"url\":\"https://github.com/googlesamples\",\"avatar\":\"https://avatars2.githubusercontent.com/u/7378196?s=96&v=4\",\"repo\":{\"name\":\"easypermissions\",\"description\":\"Simplify Android M system permissions\",\"url\":\"https://github.com/googlesamples/easypermissions\"}}]"

    @Before
    fun setUp() {
        githubTrendingFragmentViewModel = GithubTrendingFragmentViewModel(appRepository)
    }

    @Test
    fun test_ShowGithubTrendingUsers() {
        val language = "java"
        val since = "weekly"

        val users = Gson().fromJson(mockedData, Array<User>::class.java)
        Mockito.`when`(appRepository.getTrendingUsers(language, since))
            .thenReturn(Observable.just(Response.success(users.toList())))
        githubTrendingFragmentViewModel.getUsers().observeForever(observer)
        githubTrendingFragmentViewModel.getTrendingUsers(language, since)
        assert(githubTrendingFragmentViewModel.getUsers().value!![0].name == "Google")
    }
}