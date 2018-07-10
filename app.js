App({
  globalData: {
    openId: null,
    userInfo: null,
    longitude: null,
    latitude: null
  },
  onLaunch: function(options){
    var self = this;
    console.log("场景值" + options.scene)
    wx.login({
      success: function (res) {
        if (res.code) {
          //获取openId
          wx.request({
            url: 'https://api.weixin.qq.com/sns/jscode2session',
            data: {
              //小程序唯一标识
              appid: 'wxfb56f7506a576cf3',
              //小程序的 app secret
              secret: '5e961359ec8cd3648d6f9de665a4b698',
              grant_type: 'authorization_code',
              js_code: res.code
            },
            method: 'GET',
            header: { 'content-type': 'application/json' },
            success: function (openIdRes) {
              console.log("登录成功返回的openId：" + openIdRes.data.openid);
              self.globalData.openId = openIdRes.data.openid;
              // 判断openId是否获取成功
              if (openIdRes.data.openid != null & openIdRes.data.openid != undefined) {
                console.log("授权成功")
              } else {
                console.info("获取用户openId失败");
              }
            },
            fail: function (error) {
              console.info("获取用户openId失败");
              console.info(error);
            }
          })
        }
      }
    });

    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              console.log("信息授权成功")
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    });
  },
  onShow: function(options){
    console.log(options.scene)
    if(options.scene==1044){
      console.log("成功从群进入")
      console.log(options.shareTicket)
      wx.getShareInfo({
        shareTicket: options.shareTicket,
        complete(res) {
          console.log(res)
        }
      })
      wx.redirectTo({
        url: '/pages/getUserInfo/getUserInfo'
      })
    }
    else{
      wx.redirectTo({
        url: '/pages/shareToGroup/shareToGroup'
      })
    }
  }
})