const app = getApp()
// pages/getUserInfo.js
Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  onShow: function (options) {
    this.getUserInfoFun()
  },
  getUserInfoFun: function () {
    var that = this;
    wx.getUserInfo({
      success: function (res) {
        console.log("userInfo:" + res)
        app.globalData.userInfo=res.userInfo
        wx.showLoading({
          title: '加载中',
        })
        wx.reLaunch({
          url: '/pages/home/home'
        })
      },
      fail: that.showPrePage
    })
  },
  showPrePage: function () {
    this.setData({
      eye: false
    })
  },
  myOpenSetting: function(){
    var that = this;
    wx.openSetting({
      success: function (res) {
        if (!res.authSetting['scope.userLocation']) {
          that.showRemind();
        }
      },

      complete: function (res) { },
    })
  },
  showRemind: function(){
    var that = this
    wx.showModal({
      title: '温馨提醒',
      content: '需要获取您的地理位置才能使用小程序',
      showCancel: false,
      confirmText: '获取位置',
      success: function (res) {
        if (res.confirm) {
          that.getAuthor();
        }
      }, fail: (res) => {
        that.getAuthor();
      }
    })
  },
  myGetLocation: function(){
    var that = this
    wx.getLocation({
      success: function (res) {
        app.globalData.longitude = res.longitude
        app.globalData.longitude = res.latitude
        console.log(res.longitude)
        console.log(res.latitude)
        
      },
      fail: function () {
        wx.getSetting({
          success(res) {
            if (!res.authSetting['scope.userLocation']) {
              that.myOpenSetting();
            }
          }
        })
      }
    })
  }
})