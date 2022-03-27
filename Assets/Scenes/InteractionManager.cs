using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class InteractionManager : MonoBehaviour
{
    public int currentScreen = 0;
    public List<GameObject> screens = new List<GameObject>();


    public void ChangeScreen(int newScreen)
    {
        screens[currentScreen].SetActive(false);
        screens[newScreen].SetActive(true);

        currentScreen = newScreen;
    }
}
