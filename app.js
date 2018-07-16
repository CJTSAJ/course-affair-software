App({
  globalData: {
    identity: 'noExist',
    openId: null,
    userInfo: null,
    longitude: null,
    latitude: null,
    sessionKey: null,
    openGId: null
  },
  onLaunch: function (options) {
    var self = this;
    console.log(options.scene)

    if (options.scene != 1044) {
      wx.reLaunch({
        url: '/pages/shareToGroup/shareToGroup',
      })
    }
    else{
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
                console.log("登录成功返回的session_key：" + openIdRes.data.session_key);
                // 判断openId是否获取成功
                if (openIdRes.data.openid != null & openIdRes.data.openid != undefined) {
                  console.log("授权成功");
                  self.globalData.openId = openIdRes.data.openid;
                } else {
                  console.info("获取用户openId失败");
                }
                if (openIdRes.data.session_key != null & openIdRes.data.session_key != undefined) {
                  console.log("授权成功");
                  self.globalData.sessionKey = openIdRes.data.session_key;
                } else {
                  console.info("获取用户session_key失败");
                }
                console.log("onlunch:成功从群进入")
                console.log(options.shareTicket)
                wx.getShareInfo({
                  shareTicket: options.shareTicket,
                  complete(res) {
                    console.log(res)
                    wx.request({
                      url: 'http://207.148.114.118:8080/hibernateSpringDemo/decode/decodeGid',
                      data: {
                        encryptedData: res.encryptedData,
                        iv: res.iv,
                        session_key: self.globalData.sessionKey
                      },
                      method: 'GET',
                      header: { 'content-type': 'application/json' },
                      success: function (res) {
                        console.log("返回的opengid:" + res.data.openGId);
                        self.globalData.openGId = res.data.openGId;
                        wx.request({
                          url: 'http://localhost:8080/getIdentity',
                          data: {
                            openid: self.globalData.openId,
                            opengid: res.data.openGId
                          },
                          method: 'POST',
                          header: { 'content-type': 'application/json' },
                          success: function (res) {
                            var isExist = res.data.isExist;
                            console.log("是否存在:" + isExist);
                            if (isExist == "true") {
                              self.globalData.identity = res.data.identity;
                              wx.redirectTo({
                                url: '/pages/home/home',
                              })
                            }
                            else {
                              wx.redirectTo({
                                url: '/pages/authority/authority',
                              })
                            }
                          }
                        })
                      }
                    })
                  }
                })
              },
              fail: function (error) {
                console.info("获取用户信息失败");
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
          /*else{
            wx.redirectTo({
              url: '/pages/authority/authority'
            })
          }*/
        }
      });
    }
  },

  onShow: function (options) {
    /*if (options.scene == 1044) {
      console.log("onShow执行")
      console.log(this.globalData.openId)
      
      wx.redirectTo({
        url: '/pages/getUserInfo/getUserInfo'
      })
    }
    else {
      wx.redirectTo({
        url: '/pages/shareToGroup/shareToGroup'
      })
    }*/
  }
})