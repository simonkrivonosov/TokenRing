# TokenRing
1. Система состоит из N пронумерованных от 0 до N-1 узлов (потоков). Узлы упорядочены по порядковому номеру. После состояния N-1 следует узкл 0, т.е. узлы формируют кольцо. 
2. Соседние в кольце потоки могут обмениваться пакетами. Обмен возможен только по часовой стрелке. 
3. Каждый поток, получив пакет от предыдущего, отдает его следующему.
4. Пакеты не могут обгонять друг друга.
