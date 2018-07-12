Page({
  data: {
    showModal: false,
    signCode: null
  },
  toPerson:function(){
    wx.navigateTo({
      url: '/pages/person/person',
    })
  },
  sign:function(){
    this.setData({
      showModal: true
    })
  },
  hideModal:function(){
    this.setData({
      showModal: false
    })
  },
  onCancel:function(){
    this.hideModal();
    this.setData({
      signCode: 0
    })
  },
  inputChange:function(e){
    this.setData({
      signCode: e.detail.value
    })
  },
  onConfirm:function(){
    this.hideModal();
    wx.request({
      url: '',
      data: this.data.signCode,
      method: 'POST',
      success: function (res) {
        console.log('submit success');
      },
      fail: function (res) {
        console.log('submit fail');
      },
      complete: function (res) {
        console.log('submit complete');
      }

    })
  },
  toNotice:function(){
    wx.navigateTo({
      url: '/pages/allNotice/allNotice',
    })
  },
  toHomework:function(){
    wx.navigateTo({
      url: '/pages/allHomework/allHomework',
    })
  },
  modalinput: function () {
    this.setData({
      hiddenmodalput: !this.data.hiddenmodalput
    })
  },
  //取消按钮  
  cancel: function () {
    this.setData({
      showModal: false
    });
  },
  //确认  
  confirm: function () {
    this.setData({
      showModal: false
    })
  }
})