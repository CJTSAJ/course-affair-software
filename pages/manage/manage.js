// pages/manage/manage.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isTa: false
  },
  /*
  * 跳转设置管理员页面
  */
  toManagerSetting:function(){
    wx.navigateTo({
      url: '/pages/allManager/allManager',
    })
  },
  toAllStudent: function(){
    wx.navigateTo({
      url: '/pages/allStudent/allStudent',
    })
  },
  toSignSituation:function(){
    wx.navigateTo({
      url: '/pages/signSituation/signSituation',
    })
  },
  onShow:function(){
    if(app.globalData.identity == 'ta'){
      this.setData({
        isTa: true
      })
    }
  }
})