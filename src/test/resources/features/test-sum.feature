Feature: 動物數量加總

  Scenario: 傳入動物和數量表格，計算總和
    Given the following animals:
      | name | count |
      | cat  | 2     |
      | dog  | 3     |
      | bird | 5     |
    When I sum the animal counts
    Then the total should be 10