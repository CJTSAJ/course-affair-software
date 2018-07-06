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
    wx.showShareMenu({
      withShareTicket: true,
    });
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
            that.setData({
              encryptedData: res.encryptedData,
              iv: res.iv
            })
            //后台解密，获取 openGId
          }
        })
      }
    }
  }
})