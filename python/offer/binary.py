# 题目2：二进制加法
def addBinary(a: str, b: str) -> str:
    # a: 1111, b: 1100
    l = max(len(a), len(b))
    result = str()
    carry = int(0)
    i = 0
    while i < l:
        if i < len(a):
            carry += int(a[len(a) - 1 - i])
        if i < len(b):
            carry = carry + int(b[len(b) - 1 - i])
        result += str((carry % 2))
        carry = carry // int(2)
        i = i + 1

    if carry > 0:
        result += '1'

    return ''.join(reversed(result))


if __name__ == '__main__':
    print(addBinary("1111", "1000"))
