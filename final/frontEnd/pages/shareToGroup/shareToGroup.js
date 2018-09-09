const app = getApp();
// pages/shareToGroup/shareToGroup.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    encryptedData: null,
    iv:null
  },
  onLoad: function(options){
    /*console.log('sharetogroup')
    wx.showLoading({
      title: '加载中',
      mask: false
    })
    if (options.scene == 1044){

    }*/
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    console.log("获取openid:" + app.globalData.openId)
    wx.showShareMenu({
      withShareTicket: true,
    });
    var that = this
    return {
      title: "分享到群",
      path: '/pages/home/home',
      success(res) {
        wx.getShareInfo({
          shareTicket: res.shareTickets[0],
          success(res) {
            console.log(res.encryptedData)
            console.log(res.iv)
            console.log(app.globalData.sessionKey)
            that.setData({
              encryptedData: res.encryptedData,
              iv: res.iv
            })
            wx.request({
              url: 'http://207.148.114.118:8080/courseAffair/decode/decodeGid',
              data: {
                encryptedData: res.encryptedData,
                iv: res.iv,
                session_key: app.globalData.sessionKey
              },
              method: 'GET',
              header: { 'content-type': 'application/json' },
              success: function (res) {
                console.log(res.data.openGId);
                app.globalData.openGId = res.data.openGId;
              }
            })
            //后台解密，获取 openGId
          }
        })
      }
    }
  }
})