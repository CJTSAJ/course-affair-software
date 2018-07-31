Page({

  /**
   * 页面的初始数据
   */
  data: {
    key: null,
    content: null,
    testId: null,
    questionId: null,
    choiceContent: [],
    studentChoose: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
    this.setData({
      key: options.key,
      content: prevPage.getQuestionContent(options.key),
      testId: options.testId,
      questionId: options.questionId,
      studentChoose: prevPage.data.studentChoose[options.key]
    })
    wx.request({
      url: 'http://207.148.114.118:8080/courseAffair/getQuestionDetail',
      data: {
        testId: self.data.testId,
        questionId: self.data.questionId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        let chContent = [];
        for (var i in res.data) {
          let obj = {
            choiceNo: res.data[i][0],
            choiceCo: res.data[i][1],
            choiceLetter: String.fromCharCode(parseInt(res.data[i][0])+65)
          }
          chContent.push(obj);
        }
        self.setData({
          choiceContent: chContent
        })
      },
      fail: function (error) {
      }
    })
  },

  choose: function(event){
    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
    var choose = prevPage.data.studentChoose;
    var chooseSubmit = prevPage.data.studentChooseSubmit;
    choose[this.data.key] = String.fromCharCode(parseInt(event.target.id) + 65);
    chooseSubmit[this.data.key] = parseInt(event.target.id);
    prevPage.setData({
      studentChoose: choose,
      studentChooseSubmit: chooseSubmit
    });
    wx.navigateBack({ changed: true });
  }
})