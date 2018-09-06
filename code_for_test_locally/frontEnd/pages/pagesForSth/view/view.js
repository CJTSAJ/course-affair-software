// pages/pagesForSth/view/view.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title: null,
    view: null,
    wxId: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },

  inputTitle: function(e){
    this.setData({
      title: e.detail.value
    })
  },

  inputWxId: function (e) {
    this.setData({
      wxId: e.detail.value
    })
  },

  inputView: function (e) {
    this.setData({
      view: e.detail.value
    })
  },

  cansel: function () {
    wx.navigateBack({
      changed: true
    });
  },

  confirm: function (){
    if (this.data.title == null || this.data.title == ''){
      wx.showModal({
        content: '未设置标题',
        showCancel: false
      })
    }
    else if (this.data.wxId == null || this.data.wxId == ''){
      wx.showModal({
        content: '未设置微信号',
        showCancel: false
      })
    }
    else if (this.data.view == null || this.data.view == ''){
      wx.showModal({
        content: '未填写意见内容',
        showCancel: false
      })
    }
    else(
      wx.showModal({
        content: '是否提交',
        success: function (res) {
          if (res.confirm) {
            wx.showModal({
              content: '感谢您提出的宝贵意见',
              showCancel: false
            })
          }
        }
      })
    )
  }

})