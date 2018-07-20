// pages/authorizeLocation/authorizeLocation.js
Page({

  handler:function(e){
    if (e.detail.authSetting["scope.userLocation"]){
      wx.showToast({
        title: '授权成功',
        icon: 'success',
        duration: 5000
      })
      wx.navigateBack({
        delta: 1,
      })
    }
  }
})