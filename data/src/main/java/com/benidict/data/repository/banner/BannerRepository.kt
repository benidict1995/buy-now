package com.benidict.data.repository.banner

import com.benidict.buy_now.banner.Banner
import com.benidict.buy_now.source.banner.BannerRemoteSource
import javax.inject.Inject

class BannerRepository @Inject constructor(private val bannerRemoteSource: BannerRemoteSource) {

    suspend fun getBanners(): List<Banner> {
        return bannerRemoteSource.getBanners()
    }
}