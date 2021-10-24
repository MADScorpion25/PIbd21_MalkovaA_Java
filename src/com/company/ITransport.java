package com.company;

public interface ITransport {
    /// <summary>
    /// Установка позиции
    /// </summary>
    /// <param name="x">Координата X</param>
    /// <param name="y">Координата Y</param>
    /// <param name="width">Ширина картинки</param>
    /// <param name="height">Высота картинки</param>
    void SetPosition(int x, int y, int width, int height);
    /// <summary>
    /// Изменение направления пермещения
    /// </summary>
    /// <param name="direction">Направление</param>
    void MoveTransport(Direction direction);
}
