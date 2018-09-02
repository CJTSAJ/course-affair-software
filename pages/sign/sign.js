const app = getApp()

// pages/sign/sign.js
Page({
  data: {
    Length: 4,        //输入框个数
    isFocus: true,    //聚焦
    Value: "",        //输入的内容
    ispassword: false, //是否密文显示 true为密文， false为明文。
    buttonText: "签到",
    hasLocation: false,
    location: null

  },
  Focus(e) {
    var that = this;
    console.log(e.detail.value);
    var inputValue = e.detail.value;
    that.setData({
      Value: inputValue,
    })
  },
  Tap() {
    var that = this;
    that.setData({
      isFocus: true,
    })
  },
  formSubmit(e) {
    var signCode = e.detail.value.password;
    if(signCode.length != 4){
      wx.showModal({
        title: '提示',
        content: '签到码必须为四位',
      })
    }
    else{
      var self = this;
      if (app.globalData.identity == "student") {
        wx.request({
          url: app.globalData.serverUrl + 'sign',
          data: {
            opengid: app.globalData.openGId,
            openid: app.globalData.openId,
            signCode: signCode,
            latitude: self.data.location.latitude,
            longitude: self.data.location.longitude
          },
          method: 'POST',
          header: { 'content-type': 'application/json' },
          success: function (res) {
            console.log(res.data);
            var reason = '';
            if(res.data.result == "fail"){
              if (res.data.reason == "time"){
                reason = "签到已到期"
              } else if (res.data.reason == "signCode"){
                reason = "签到码错误"
              }else{
                reason = "距离老师超过100米"
              }

              wx.showModal({
                title: '签到失败',
                content: reason,
              })

              /*if (reason == "距离老师超过100米"){
                wx.showToast({
                  title: '签到成功',
                  icon: 'success',
                  mask: false,
                  success: function () {
                    wx.navigateBack({
                      delta: 1
                    })
                  }
                })
              }
              else{
                wx.showModal({
                  title: '签到失败',
                  content: reason,
                })
              }*/
            }else{
                wx.showToast({
                  title: '签到成功',
                  icon: 'success',
                  mask: false,
                  success: function(){
                    wx.navigateBack({
                      delta: 1
                    })
                  }
                })
            }
          }
        })
      } else if (app.globalData.identity != "student") {
        wx.request({
          url: app.globalData.serverUrl + 'addSign',
          data: {
            opengid: app.globalData.openGId,
            signCode: signCode,
            latitude: self.data.location.latitude,
            longitude: self.data.location.longitude
          },
          method: 'POST',
          header: { 'content-type': 'application/json' },
          success: function (res) {
            wx.showToast({
              title: '成功',
              icon: 'success',
              duration: 2000,
              mask: false,
              success: function () {
                setTimeout(function () {
                  wx.navigateBack({
                    delta: 1
                  })
                },2000)
              }
            })
          }
        })
      }
    }
    console.log(signCode);
  },
  onShow:function(){
    if(app.globalData.identity != "student"){
      this.setData({
        buttonText: "发起签到"
      })
    }
    var that = this;
    wx.getLocation({
      success: function (res) {
        console.log(res)
        /*AMap.convertFrom(res.longitude, res.latitude, 'gps', function(status, res){
          console.log("转换成功")
          console.log(res)
        })*/
        wx.request({
          url: 'https://restapi.amap.com/v3/assistant/coordinate/convert',
          data:{
            key: "5866a861c514947f8147e38c8ccaf2aa",
            locations: res.longitude + ',' + res.latitude,
            coordsys: 'gps'
          },
          method: 'GET',
          header: { 'content-type': 'application/json' },
          success:function(res){
            console.log("转换成功")
            console.log(res)
            var location = res.data.locations.split(",");
            var longitude = location[0];
            var latitude = location[1];
            console.log(longitude)
            console.log(latitude)
            that.setData({
              hasLocation: true,
              location: {
                longitude: longitude,
                latitude: latitude
              }
            })
          }
        })
      },
      fail:function(){
        wx.showModal({
          title: '提示',
          content: '签到功能需要获取您的地理位置，请确认授权',
          success: function (res) {
            if (res.confirm) {
              wx.navigateTo({
                url: '/pages/authorizeLocation/authorizeLocation',
              })
              
            }
            else{
              /*返回上级页面*/
              wx.navigateBack({
                delta: 1,
              })
            }
          }
        })
      }
    })
  }
})

/*wx.openSetting({
                success:function(data){
                  if (data.authSetting["scope.userLocation"] == true){
                    wx.showToast({
                      title: '授权成功',
                      icon: 'success',
                      duration: 5000
                    })
                    wx.getLocation({
                      success: function(res) {
                        that.setData({
                          hasLocation: true,
                          location: {
                            longitude: res.longitude,
                            latitude: res.latitude
                          }
                        })
                      },
                    })
                  }else{
                    wx.showToast({
                      title: '授权失败',
                      icon: 'success',
                      duration: 5000
                    })
                  }
                }
              })
              var myDate = new Date();
    console.log(myDate.getFullYear() + '/' +  myDate.getMonth() + '/' + myDate.getDay() + myDate.toLocaleTimeString());*/