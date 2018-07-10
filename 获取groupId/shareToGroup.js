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

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("获取openid:" + app.globalData.openId)
    wx.showShareMenu({
      withShareTicket: true,
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    var that = this
    return {
      title: "分享到群",
      path: '/pages/shareToGroup/shareToGroup',
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
              url: 'http://localhost:8080/decode/decodeGid',
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
          }
        })
      }
    }
  }
})