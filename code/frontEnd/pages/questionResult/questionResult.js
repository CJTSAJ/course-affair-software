Page({

  /**
   * 页面的初始数据
   */
  data: {
    content: null,
    testId: null,
    questionId: null,
    choiceContent: [],
    studentChoose: null,
    correctAnswer: null,
    wrongChoose: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
    this.setData({
      content: prevPage.getQuestionContent(options.key),
      testId: options.testId,
      questionId: options.questionId,
      studentChoose: String.fromCharCode(parseInt(options.studentChoose) + 65),
      correctAnswer: options.correctAnswer,
    })
    if (options.studentChoose != options.correctAnswer){
      this.setData({
        wrongChoose: options.studentChoose,
      }) 
    }
    else if (options.studentChoose == -1){
      this.setData({
        wrongChoose: -1,
      })
    }
    else{
      this.setData({
        wrongChoose: -2,
      })
    }
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
})