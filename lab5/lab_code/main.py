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
    data = np.array([[1, -0.541, -0.569, 1.766],
                     [1, -0.609, -0.184, 1.141],
                     [1, -0.470, ]
                     ])
    kmeans.fit(data)
    print(kmeans.labels_)

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
