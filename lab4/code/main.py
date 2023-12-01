# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
import numpy as np
import matplotlib.pyplot as plt

def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


def Euler_method(fun, t, x_0, dt, args={}):

    t_points = np.arange(t[0], t[1], dt)

    if type(x_0) == type(np.array([1])):
        x_points = np.array([np.zeros(len(x_0)) for i in range(len(t_points))])
        answer = np.zeros(2)
    '''else:
        x_points = np.zeros(len(t_points))
        lm = len(x_0)
        answer = np.zeros(lm + 1) '''

    x_points[0] = x_0

    for i in range(len(t_points) - 1):
        x_points[i + 1] = x_points[i] + dt * fun(t_points[i], x_points[i])

    answer[0] = t_points
    answer[1] = x_points
    print(t_points)
    print(x_points)

    return answer


def f(t,x):
    return (-2 * x / t - t ** 4 * x ** 3 * np.exp(t))




# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    dt = 0.1
    t_for_eu = [1, 1.5]
    args = {}
    x_for_eu = [1 / (2 * np.exp(1))]
    fr_euler = Euler_method(f, t_for_eu, x_for_eu, dt, **args)
    t_n = fr_euler[0]

    plt.plot(t_n, 1 / (t_n ** 2 * np.sqrt(2 * np.exp(t_n) + 4 * np.exp(2) - 2 * np.exp(1))))
    plt.plot(t_n, fr_euler[1])
    plt.xlabel("t")
    plt.ylabel("x")
    plt.show()

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
