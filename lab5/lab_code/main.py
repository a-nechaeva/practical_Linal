# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
from sklearn.cluster import KMeans
import numpy as np

def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    kmeans = KMeans(n_clusters=4)
    d = np.array([[1, -0.541, -0.569, 1.766],
                  [1, -0.609, -0.184, 1.141],
                  [1, -0.470, -0.736, 1.718],
                  [1, -0.329, -0.803, 0.976],
                  [1, -0.497, -0.614, 1.569],
                  [1, -0.493, -0.594, 1.470],
                  [1, -0.933, 1.570, -1.573],
                  [1, -0.933, 1.570, -1.573],
                  [1, -0.850, 1.096, -0.799],
                  [1, 0.375, -0.885, -1.742],
                  [1, 0.173, -1.026, -1.333],
                  [1, 0.276, -1.160, -2.090],
                  [1, 0.355, -0.944, -1.819],
                  [1, 0.564, -0.419, -1.217],
                  [1, 1, 1, 1],
                  [1, 0.911, 0.698, 0.508],
                  [1, 1, 1, 1],
                  [1, 1, 1, 1]
                  ])

    kmeans.fit(d)
    print(kmeans.labels_)

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
