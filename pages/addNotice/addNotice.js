const app = getApp();
// pages/addNotice/addNotice.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    content: '',
    openid: null
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
  
  },
  cancel:function(){
    wx.showModal({
      title: '提示',
      content: "是否放弃本次编辑？",
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定')
          wx.redirectTo({
            url: '/pages/allNotice/allNotice',
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },
  getContent:function(e){
    var c = e.detail.value;
    console.log(c)
    this.setData({
      content:c
    })
  },
  confirm:function(){
    var id = app.globalData.openId;
    var content = this.data.content;
    wx.request({
      url: 'https://127.0.0.1:8080/addNotice',
      data: {
        openid: id,
        content: content
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        wx.redirectTo({
          url: '/pages/allNotice/allNotice',
        })
      },
      fail: function (error) {
        
      }
    })
  }
})