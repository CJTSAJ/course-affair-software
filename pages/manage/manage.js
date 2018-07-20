// pages/manage/manage.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },
  /*
  * 跳转设置管理员页面
  */
  toManagerSetting:function(){
    wx.navigateTo({
      url: '/pages/allManager/allManager',
    })
  }
})