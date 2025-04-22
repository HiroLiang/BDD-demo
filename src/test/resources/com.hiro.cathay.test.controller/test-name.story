Meta:
@name

Scenario: 測試 JBehave
Given 給一個字 <str>
When 呼叫 API
Then 取得結果: <expected>

Examples:
|str|expected|
|Hiro|Hello Hiro|
|Jack|Hello Jack|
