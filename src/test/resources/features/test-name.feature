Feature: 測試 Hello Name

  Scenario Outline: 傳入姓名，取得 Hello 姓名
    Given 你的名字是: "<name>"
    When 呼叫 api
    Then 取得回應: "<response>"

    Examples:
      | name   | response     |
      | Alice  | Hello Alice  |
      | Bob    | Hello Bob    |
      | 小明   | Hello 小明    |