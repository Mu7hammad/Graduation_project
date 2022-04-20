package com.example.graduationproject.MainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.graduationproject.ListAdapters.EventsAdapter;
import com.example.graduationproject.Models.EventItem;
import com.example.graduationproject.Models.TimeTableItem;
import com.example.graduationproject.Others.StaticVar;
import com.example.graduationproject.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NewsFragment extends Fragment {

    RecyclerView list_view2;
    ProgressBar events_progress;
    TextView nothing_events_tv;
    Animation show_animation;
    List<EventItem> events_items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        declaration(view);
        get_events_items();
        load_events();

        return view;

    }

    private void declaration(View v) {
        list_view2 = v.findViewById(R.id.list_view2);
        events_progress = v.findViewById(R.id.events_progress);
        nothing_events_tv = v.findViewById(R.id.nothing_events_tv);
        show_animation = AnimationUtils.loadAnimation(getActivity(), R.anim.show_animation);
    }

    private void load_events() {
        if (events_items.size() > 0) {
            sort_list_by_date();
            Collections.reverse(events_items);
            /* adapter */
            EventsAdapter itemsAdapter = new EventsAdapter(getActivity(), events_items);
            list_view2.setAdapter(itemsAdapter);
            /* don't forget linear layout manager with recycler view */
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            list_view2.setLayoutManager(llm);
            /* update ui */
            events_progress.setVisibility(View.GONE);
            list_view2.setVisibility(View.VISIBLE);
        } else {
            /* update ui */
            events_progress.setVisibility(View.GONE);
            nothing_events_tv.setVisibility(View.VISIBLE);
        }
    }

    private void sort_list_by_date() {
        Collections.sort(events_items, new Comparator<EventItem>() {
            @Override
            public int compare(EventItem o1, EventItem o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }

    private void get_events_items() {
        /* TODO Get data from database */
//        new Handler().postDelayed(() -> {
            events_items.clear();
            events_items.add(new EventItem("News 1", "News 1 discreption", "2022 / 05 / 33", ""));
            events_items.add(new EventItem("News 2", "News 2 discreption", "2022 / 05 / 15", ""));
            events_items.add(new EventItem("News 3", "News 3 discreption", "2023 / 05 / 18", "data:image/jpeg;base64,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"));
            events_items.add(new EventItem("News 4", "News 4 discreption", "2022 / 05 / 18", "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBQVFBcTFRUYFxcaGxobGhobGxskGxodJSQdGh0dGxsbICwkHB0pIRwbJjYmKi8wMzUzICI5PjkxPS8yMzABCwsLEA4QHhISHjIpJCkyMjI0MjI0MjIyMjIyMjIyMjIyMjIyMjIyMjIyMjQyMjIyMjIyMjIyMjIyMjIyMjIyMv/AABEIAKgBLAMBIgACEQEDEQH/xAAcAAABBQEBAQAAAAAAAAAAAAAFAQIDBAYABwj/xAA/EAACAQMCBAQEAwYFBAEFAAABAhEAAyESMQQFQVETImFxBjKBkUKhsSNSYsHR8AcUssLhFTNy8YJTkpOi0v/EABkBAAMBAQEAAAAAAAAAAAAAAAABAgMEBf/EACcRAAICAgICAgIBBQAAAAAAAAABAhEDIRIxBEETUSJhkTJCccHh/9oADAMBAAIRAxEAPwAp8SceptFlZHIYALjUAexIM+4iPrWesta+Yt5ioUTpCLhzLM0H5oyD6b1c5hYuOCCgtZDQwMQZRWYKpLb7tiSKH8Alk8QVEkAqEZiAcEtJLYnCgTie3THLc5WTCPFBrlFq6vyKqhgdRJ8xBG6IQcSYEwJIgHrebkFvS5XXrYCGbI1DbC7qZO22dqi4PldrZrjXQQV8m8A4+U4B6gnoOlEi1zUdNxArByOkYEAwYxkz9c9OrEqjTSZE3v8AF0RJyG3hmVWYgapnfPmGc5OQcGOlVLPJwhAZoUH5ekmIMxO4GDtJovw3EKABr1NpyJBz6fWYilS28yVwTk5mT6CTHr0peS4qKSQYFJttkL8PCuqN5nLGRHl1bke29R2kuSY09MfKRLe34bY6jJPSsTzvxLnEXLisy5hdJ6LiTn+Roh8PcdxZvW7TnxEMk6hJUAGSGGx6fWuZ4nFctf7N1kTfE2TpUZSrHiRvkfmP6014rq8Xym3xkYZ8CS5RK5plSNTDXpnGcKcDTRXUAPBpZpoNLNIBQaeDUdKDQBLNdqqMtSTSAkLU3VTa6KYD5rppBS0gOmnqKjqVBQMcFpQtOFdNIBCKYRTyaYTQAhptOpDRYxQaWmUtIBTSUtJQAlLFdS0DPMrHP7jA5UDTGkFpOBJ1QSJKgwTucbxVbhrtyGe2F1AEyN4wPrneY796p8N4SayDJzpIk5wynS4AIbYyDERHY3x3ANZCXLY1ardv9mwUtPn1khB5VgKMYAI9DXh8eSs70vRZ4a+GtoGB3BmRpRRspAyfm2z1q6il9LWiAMBjMDGbmGzu0z6DsCRnBcLeQnxLTKrBlUBMLpElmnzHM74xtWg5bxoaybUHw4OlxIYbkCfNCypBHQE9qeOFNphJ0tEXC80a2PDYMgWCfJMySWyTsSVMx1O0UfPOE8PUSVDABcE+41DdtxGffpQLhuKXWmk+a2VmWAlWk/hnChjmSZ1d4NPnnEWBcZltRKq7MABMx5Yzgg5/8orOc7fZcYpIzXC81cXHFwgnU3kIB0j6ZkZwa2HwvfWXaCZ2ZZwPmbHfAx1hhjesnzTlEjxFLHeHC5OnGTORmJ6wD3r034T5Rbt8LYVgDcCIzHqGln3wd3Ye1N3/AMFW7LXFIFbSTJJwRuAcDUPfqPyoXY4vU5tmBHSZJ3M7YG3eZolz3h50OraG1AlvNkAiQdJ20lvqaD/5YLxIgGADpO0LgFTq7DGY29KrFqaf7Q57i1+ghFNYU5jpABEdIBmPr1/v3qNeIU7bTHsdoPYz/c17iyR+zzHFiV004ikirJEpQa6KWKAOFOFMmnrQIWupYpQKVlUcq0/TXLTgKQUNC07TS11AUIFp4ptdQFDppK4ClpDGmuNca6gBKQ0tcaAGinCm0tAC0lLSUAdFLSV1JsZ5Jx6OQLjIV1TAicCfMzTJIgySBHptU3J+bxpOgmUe3qIB8p0zgjOJHTcZxWZuXZJB1HTMyTOPLpjoAB1jtRH4ctO/EIiqt1iRCM3zCAWU+bKwCD968aMKejus2nM+cXBbW2HDbeVdlBEy5Pm1AEqR+ZzVT/rBtwbZIOS8EjSTkkQcAfXpio+cWYuadITQh0qQ2vUQVhuhbzdMaQCB1qe44HDqhyQAulQBgRIYd8AGdo9Mzkk7e9lJKqSFtXgdVxhLkyAWkv5oLJ5ROAciZM/SJryXBqckMxZVIGdQiQR6hsnrIqGzeLsJjGqPOTp92ZtpDZkbAwJqDhkdnfSja5kANuOrAT5gc7bZ9TWCdbRfoKcNddfKX2W4pDExp8zAEA76SVE7b9K0PDcTfHn3AkCGGRpxLAEACF3+xFZTgWLB5EaRg57TOd8AyJ+4o1a4vQjEfKEMrkMxiQBpPmVYHbYd8OUr2CstvzhuJAtvA0sGOQCWX8JG4PzdvuRVVrt9bwEg299enyBdQUkdwJAx9MbAXZp1B41afE+ULnBAx0VgCNsH6bPktm1dt+Bcw74UjpB1hVziCCYgZx0FCdOwpMs8bwdxkWFhhoIJYkSDDzvI0zH8ukfLOAKhQ3zmNR3EkAMo/hBH5CiHEsWtm0gdDbVLgCGSyAkBGJ2JAnrAI+tbkPHLdcs+pbmoAKzSANJ8o2mCpgxtHrPbHyldtb6sxlhdUnouPy64NoPtv+dVLylAS4KgZJI6VoxcEgSJOQOsd47UhYElSOk+h9K0j5sl2rJfjJ9GdFdFHL/CIQTp6E4qivBhsK0HzYIPQgH9R3rph5kH3oxl48l1soRT1FWLnBXF/DPtmoCIwa3jkjL+lmTg49oUUoptKDVCHinA1HqpwoAfXLTQacDQMdFKBTLt4Isn2HvXWrw0I7EDUF3xkxiPrUcldCpj6aTSsaaaoDjSGlNNoA6aWkiloA6urqWkMSuiliuosBIropa6psDxTjeVm5duXIFlVY6g7LMnoulQPMTHpneKIcla4jIlmHKFrmtZJAgJGYaZkAiMsY7kfzXjbb+QAhtIA0sXDNJ1eVlTRgfhDT3MzUnCu/DOSCCypLKwbUh/EgYRocaTkbYBJmvIptbO49G462l9LF85GhvFCCFaCo1rO5VrjMD5pGqCJmgnMbTC4Sj+GsNGr5mWWDtgHUpyoPVl7Zqkee3PDQEl1LXCEcnVpaCVC76cbkz2Owqi/M2dzoAEwSqqBuYPmJkhSF2jcwc0pycnocPxJ+OthWLKFBOmfl0rAAYhRgavL1GxMVEM3A4uSdKgFQxcAZeNs6SR0x3qxbua1VnZVyyqrasHaCYnpuY+lLwKvrC25DMwAG8+Y4Yruo+xGaxUG+nsvkx/+aK6kEBmOGI822PaAcH0NOvNFzyEaWCmSwhSNCAQx2kuemY7RWj5Hye29h21AsHO4EFgQQQGA/ESp7gdsEZxfLbniPaKM9tBlhPmXsp6wAB9ziaiqRRX4ZEhg58gAMZGJ1jVG8b75xS8ruMrh7dwAjSLfyknZWBHTPpnPbMdpyH021mTIQiQIBnUN8GfXYdYqPh0uW5UkMEcDI3ORmPwgyInaanlS2C0Hj8XXV8QEo/ygMoKkHIJjOrYH8jMCpLfNLbOX8NS2kHSZiCI1TETtGBk/wARrPXuChYU6szMZbaeneT/AEijPwvwyAXdcDxNC+efJE/JOBIIB+lVGakLluibi+ZBLj3LTkkWwETEBwV2mASYPToaiT45e3i4vUHGCRufp/Wn865YLXDeJqLOSiySsRJOIGMe+w9Zy3Eo9wKuSZMsqz3JmJgz+lbY8cpb9EynxdHonJ/ivhL9zw1Li5c8sEyJg7Hpia0dqyFaQf3vzIP8q8k+D+CKcxsgmT+0OVAIhT2r034h482OGu3lEsiyoO0khRP1NXKNOioytWOv800XHVkIVROoCZ+3ckRUicfauNokMdWmI6+3QTivJbPxpxgbW1wsOqmYj2MrXpHw09jirK3VSGBgxODvIB6Z/KluPaDT6Cj8DbYwpgiJAPfIxQx0QEgPt1YFRvG5xvj6UM4/mXD8LxHhG46MujqIKGGgSRHaM1fsc1e7Ko9q710kEEgeh3HU1rDyZx9/yZywxfocLZORkdwQR+VKKkXiEt6C9tlYZlOu5IKiPT70QfirJjXAJQP98R3nB6dK6Y+Y/wC5fwZPx16YMFPWiT8AhyDH6VTewQSoywG2AT161vHysb90ZPDJegL8Q8QERdWxJkyYGIyRsMxPqaDcDxhZtTsCiyQSpPlBAnSMap6en2J/FnDXDZViumJLSJ07H5lxGIPTPpIy3Krgh3ckhSW9NUHST1JkAR1iuLyJXO09GsI1HZveG4oXAP3oyO3v61Y01nfhjiFAicE9huf4h0nAX0nFaMuNWmc/p1z23FduLJyim2YTjUqQwiuipitNK1tZBHXU7TXaaLAbFdTtNKFpANpKfFNigoSup0V2mpA+eLF5lUkNuQpBIMjfKR5lx7TFFOA4O5c1KqkLp1LqDIrKJyB8uoQWmcaTvmLPBcBbYqEtv5gQTcYaVG++k5xviP1M2OBa24ey6asOEmChk6gARuQM9SGPavHnk1SOyjLOoLF5LZwQAInrP3iM9cHFS8Dy/wAR1tqVlnZQDhmMGNsmZxP/ABVvnNzxNQXSVDF5CaQGmGUAQvb8OTnrBj5WiggvIgYGDO+YM4x06fShy0FbDXB8MbZ0O6sy5B+ZTMMR0nM5+ntbtWriOwXUGwDAjfLkgY0FSAO5IjaqfHBSisIbHzqTC77yI9Pp13q/yRgttoc3MSdS6QLYbfJ6mfv6TWPNtUaKKTs0PKrieCqp83iLso8p2nYE7K0TP5zV4XmaPcvKru7u8qVMBQq6S0qI0+UmY6GIzPcVca4VsqRbbcXIIDKTJCqAJLZ85x21TihwfG2LLwq6kVgGUgEE7Su4bzAkNEwd8xQmktljuKUK5uWm8QroIbSF0z8oBBhd9vTMnNVLzlndmuByfKBEfLuZG42j6/UhxNthw926fxMoQ5mRGw6bnI/Kgd9ywZMeUCc/bpv1M9zUSjbJYniAZLkdl1YB1GQ2CRsTVmxxlzTqywznpEeaMYhQevagyCPMTqGTtkHpAnIq/ZsXriqiW7ptqxBZAfDVzpZiWgjAKdPamokBJ+Z+Jb0m5sAdIGAR16gbjHqdqi4fiyEJ8oOBiZ9CQB0kf07g1fQQwXYurADIAiYEgNv3xFSX+KJeApEjKnfEjbpiMelW1+NIn3Zr/h68Ll+2Tl1DkH0KnqPcUd+LVZuDuosmdGAT++k4rPfAcXOIghfJbbI1A5MCZNbH4ivjhrDX1BYqUGnvLBe4796tLg1+jSO419nka8IVDDeRFb//AA14kJZurBjxPtMnr71SPxbw74u2R9Vn9VI/Ojnw3dsXVuNw1vSJBeIAJMiRmPw+lb5c0ZxqqZMMbi7u0ZD/ABHt6+LLrObdvH3G2ab/AIXNp411/h/k5/kKJ/GdhhxCnSf+2vT+JxvVD4cYDirR7kr+Va/Eni5X6M/kfPjQc/xI4+7Yu2LltympHBjrBXeM/ip3whzN79xluabgKhgIAYuBIzuMA4Peo/jywGt2mOdLON/3gD/trOcm4kWbuseUEQYPSIaD0JE/WuaWNqCmjXn+XE2nOeY8PaurbPio5GsriBJIBGYJw2KL8Zx9tUXiVBdYiRuAJ1SJxAnoftWO+Mb1p7wuyJFvQCD/ABFpPUfNGJqpy7mUooN1rYUsfKs7+SQJA6r1iD9Kxcr6ZVmi+JecWrwWzbuESAQQfK2qIQgMD/7PY1lbnEKEtSwChQoUD5fUdB+LfPUx0D8xJt3QrFlCkxKnUYO2mPKTAx0ruJvCFUvMwDiN8zjE5od1QrCfCeW4ugtuZ0mQpOZWT1AjUTG/U1sfhq5b/aIpYvILFhBOJ+46+pME71gbN/EE5hRJXA6TA6aeoImiXKuYNaYspCtEd8bk5zqgD+4rTFmcGm0ZzhyVG75rxOhDoYBwVMdYmciDggEVPw13xFDwRPQ9KwvOOc3AgtkLAUAtA15zGcHoYA+tFvhv4g1IqOsBFg6c5xEajIgT3+gArth5cZSt6RlLA6pdmpaACTsASa5YIkZB2NC+P5paK6Tt5S2pekwI6apznGKn5JxOtSpCrBhQrD6wN8EgScmuhZoylSZm8UkraLjQMkge9LFVebITpVXtq4OpdaBjiT5ZIg4qHl3MrZthmdoAWblwAKWO6gjEj6+9U5JOmTx1ZfK0gWnh1IkMsd5FOAnb++tPkmFURaaXTUumo7jwY0MfURH60WCR4rxPN0tqbIt22E/OpDMWGAQSzQsY0k9B1po5syy6qrzmCDCNMDCkSBPaJPXM5y/CnSZI9tvbOVqbhrjs4IeAI8zmdIECfpOI6e1eT8ao67D3MuKXiFW5pKsSQVUzPzN5lgHEYadokHNVeHsdLbNAAaW6EHTn8JwZGCfrt3FW38NbizDFhKGFEa4IyWEsGMGQYJmSIu8PwvlQhVcOAVggSNgXAOPrnsOwo+hv7K/E8tuXHlSQSAAIcyexKggYMdsHPWt3yflLmyl99TIttfCXI1QCyu8RqJYKw3A1bSBArl1zzhLkWk3uaFI1Lu0sSWbpABj0rX8x5zaWdMJbtyihXhy+gN5bZHyqOuxIMUZYcdIuErRl+J4lQZE+bYMAYDZ83Q+ULE+vUVNZ4C3q0vdDJJcaSR5iYid99QMjqKM8bwSNw1trlrzLaa6CB85Hhr5tJ1bXJz1EziKzq8H4lpXtlhbZgrB5kHHzdAJLHEdTXO1RSLHFc1t3FThnYi2pB1DSpP4Jz1n9D9M3x1uW8haImCwJ7bhV7VNedmdrZUgjLPEyOkHUAAZOMnJqtatuSWKQOxO35/3NNN1THFcmWOB4VyPNgMC3SOuWBGRgj6/d9rmL2x4aDQrmG0SJBOSRIWfUiO+1XDcMQBA0hfXb+u9COPcrogElzGCdonpnp0oV9GkoRUTuPdYW2AJEsS2nVncal3yOpJP1pvIeFa9xC21zMkkyYjJk/u9PtVK5cC2xvBG6wR0HUY7Tvn1rR/4e8OTcNxvEhQdJUEjoDOZiCNhuc9jfS0c1Wzbci5a1i+9xoKsgUadwZJMzGIIq/wDEl+2/DsrgaSy/MBG89cdKU3l/iHurAfcigvxZeU8OQGBOtcAgnr0FXCTnkTl9lSjxi6Mbf5aNYRWnyzKnEy2N/QVsv8MhFu6vWR+r1iCpEb/atV8D58Xr/wBv/dXZ5ONKHJGGGbcqY7/EaRftMMTbj1wzHcf+VBvhjiWPF2w5JHzCWJyGXOfSa39/hbb5uIjR+8AYH12oe3A8LauKy2wHiU0bmTEATG8Vx/IuNVs6OLu7HfHZt+BACF0uIxXqAQyyQpB3IrAWQLjgKoBALZ1RiOhGfvWz59aY+KXMs9oMT0lHDQPQAD3yepoGY8IsshlBggnGenQCMYqfk/Hjsv4rd6BfG3WVNJWFwB3nBwx3H946U+E4ooUaCDIIP/vE/Ln02qXmAm34hLEkqASR5SMnAExDfTT1nA29xB0BtRI2gbnpmdt4/s1nGJEtMt8xvqzCAJyYB9IA+wmqBuwQDsBAmcAEnpuf61Abs5kdO/8Af9imuVjHRhmDJ26bR/eKtaVEBC1ePy9x0zOexGe33owtwKGUzO+2caoUA/hj02HtOfZeokmcZz/SiPD3EKrgs2SRA6SAMe9TKI0wg+m4IKDUNIiYJHXVPXG8doFLypLiuArloUgsY0qYxAOWgzPT2oWoTNxpGYEAAHqYOrEf0qbh74iZOJY76okgt7b4mPXMVLToaewjxPHsrMLhRyR2JXuCM7yMzIxVjh+bhcvbYk7FYmPusnf2iha3VNwEzo8xG2NiMAHoMiDmjSXLDW2cq0LGgEDUZwZIaBBJEbQfWqVeylJplvhufWwBFy5bkxkFc9Plmrl/jQzMrXFJB80nIO8QTj5p9ZrLHhxd1L5tIEh1ELIEqMDHQQQM/enuyXVTVB2BHrAAaT3EZ96fJoaae6Nn/nf4MdxtVq1zZAoEGQYk7AQY1ddPTEnbG9YB/h64gDWyyyN0LD7aSd+5P3pvD3OMtq9t7jC4QXtloYnTBdRM7jv1FWr7TCov0ehXOaA48TwpHlOkMPYk7fYEVmud82uWLgU3Z1KHB1E4M+mNtqyqcVxt9kseGrFzCHSVJME/MWiIkkkYrSWfhwBQL3MuGtvGVVywH1JGfSKblkZk1FdI8wcFwCCznckkaepAAkmZ77TEdaXgbYaSTpBgSBsem8DeOs4J6UnDltgTtvmI2oxw1jhyWt+G86RpZs+fHm0rEKSBuThY6k1bkSlZDc4QqjaWDD5gZOT7TERJ2/WjnB8MywqrcCjaNQnI+bpGNt/bam84seLce4ojUBIKxEQCcYk6Z2603iOS37ag+IqmBA/aAqOgyv8AP+VOGRJbV2U8d+w78J8MhvftWIGpVBJIDRN3Ory6otiR/EK13N+U2OJuInjEyLjsdaEahoTOJPzmBtg1mvhci0qvc8Q3P2mu4qlsto0BCdgFVydsnY5NaDl3HQSzG440hVYqST5nknEZHh7TtWeSfJ9FxhSodzi46XPDW9cC+GoFzSrAZuMymAv4VTdp9GElcbzDjLi2HVnCw8lCrgkjybEKQpKsBuTp2Gk0T+IeFt3Lj3GKOGZdIMgIFCo3lAjUTJ1RIgDuaDcRydzbKhbbXTgsQoUT+6ImckdsTvtFJg0xnCcCDZF1ggdtRkLgyScAbVFw1ryrO2OsUZ5qht2Rb0kaURRDLucDCnOBVdEXYzjOVA9I3xvUvZtjVHOFIiCIBOSNzgbis3ze4Ee3iY1MJJGcAGRtFaniWURBxAnAJONvTvNYzn7TeA7KMdsk1WONyHllURtq60/MAZkeYk53JJyT7zWg5NxS2wPEa4VAwEdFG/qrY+1Zrg0ls7AH+lE7d+PKoyIzW0sWjmjOj0XlnxHwdudNniGmJLlSPpLj7gVcu/FfAsIfhrjCZgpbIn2NzesIzXltrda3COYVoMH++nehj3usSe9ZQxS/waTnH1s9FfmnK3nVwjCeqoqk+5tuKbwXMuV2y3hpfTVEiW+kS5MV54OJMyMYNI95pM7yc1p8c+r0Ryj3R6Xf5vwjCFuXVkEHUrt/pcfzoJdv2lgpxAJWcst1TB3yFaO33rK8LxDCTvPenOC2Tkj+9qzeGVl840afn/NbVxF8I3XXRcV7gW4EU6QY1tAJ3kDpv2qnyk6uHMiSF/2j77UI4nmVxrfhtcciICliQMaYAJ7Yqb4SuEo67yQIzmZgfnRPHxiXjyW6IuaWfIEIEEhp7QNMUA4pIhZDZyQwYZzuDB961HPrHhuBG8bYiDB/UUAv3cMpR8ScREDE/NRF0qJyRt2D1IBxj+/emu2Ae+M9xVohQCADBCyGjJ7gidjPbeq923sV7Zz700ZNUOFyR3Pf+tEuG4ljEsT0P6ZEdo7/AKUKTHc/UVNZJho7ZHvmZ7DFDQkW+Y3CVUTlScSOu053gdO+fWvb4rSdQw0ydvQ+nQdO9RXnAJGJJXP0nc++aaF64P8AL0NStIApb4pgFIG0jI9M42zOfbrV+1xbusmSux/i2HXsY3MQM0EDnGwjsB06/wBa7iOLuTojTGDIETnBDDBBkfSpUU2M0Nj4iYL4SpHkKsygAlpWCYwV6DGZn2p2bk3IJJB2MRsdwAIAwfv7yKtG4bhIDOwbJgzM4J07Vo+Y8NxAsC54TrpA1EjPqxk6twvSKbVDVs0FvnqJbRGHnjSTsMQBnfYjNTc7RLltbtsy1tvEWCMgfOvqdOY9KwFzg+Ju6YVmLGQJMmesHHTNankPKrnhFbsKxI06WBdCMZIJBkRg+tKq9ji5WXuT8YqsqAmdN1rYnK/s3YCN8Gc1D8Ocbas2il3hxdYsWDeHaMAgYlhJzJ+tBuA4W7b462pkqHGshsRc8uozGPN0G9Cf+pXV8mu4NPlgMYEdq3hFOOzOadmdkkHTJbI9M9qKcr4vwiSbZclSvzMIEg7bUvC8KdOqQOuR/wA1Zs2HCazpGNRwffoahyLjCiWxzSNrRjtK9PWJq23N9fzW2Jky0rHbaMVTtW3EIoUt138s5yf5VMgYIV8oYQsGZkmAfYk70jRBaxzi2qQADEnJbMiOtr+dTj4p0qP2LQBHzrv3OIoZd4ZwoUaPMQowf72mpOPsvCp5SXaAACNgW3J2xS7Anb4mXH7FsScFc5mcqDHvVjhef+JdS34TLrI3YCO5MChjWbhuC2SusAMI2G2Yn3/vYh8OWS3FszsP2anI2BkDG4GD1G/3ooVhb4hOprdsZ64KnoSML7CqV2zIJBMtnCgD8zNWbzm7fCycqTuoIG3YDpmm8BfuF9JcabZCaSBB3JYhj6/pSS+y7+iG/aBgBWO/baAuaxfP7uribhG0qBgdFA6eorf8TxFthc+SBsAEB23kDGQMV5teOu656a2P0kx+Va4l+RGWTcdknDXCvnABIOQdjtg+lekfDHLuG4iwLrcOinUVw1zMRJjWYzPU7VhuX37aMddoXMwFJjJS4ux36N6FRR3lPxmOHsqn+WMFnZYcR8xxESI2qsl+iIJeza864VP8vdnWQLbkA3LhEgErgtGCAfpXm4QEZrfvxZ4nlt2+QLetHULM4ML82O/aqvxhz5+GuWm8NLgIuqPMShPkDbgTGBO2WHeIhJrQ5RTMVdtpBMgb4/4py3htH9PvFD7nFAlmIyZOd8ycnqabxN2A3t0roMwnd4ZgA8EA9YMfTvUGhiSQ+nYRAk+v99xWh+IC9vgbAIGk3EKQDubesiRsQW26jMkyBmV4ppEqBucz6miMk1sJRd6J0tgGLjTIkGAP0qX4YfS7KpIMCCD2IH+2nc2Qf5fhbgKkt4isMArp8MrMZO7Ae3qTVLkTxfIkRJE9MyR+bCs8m4s0x6kg1z1yz5loBPTO232/KgXHWQXwjHVOx6kahHtk1suHuKBDBXMehgANI9ZFZbi0C6X83QY2xIJPYwRBrGP2az+gfct+QGGysb7kbbn8qpshGcjFGEtjTjVKuNMjYHAkdDJNMcfstPh4Q5PlmQTCkT1wJA2oMnGwUoPftVjhrUg4mGG32ImIz39OtWzwqjRKsPOVYgD8QkaRO+cbe9FOX8IC721EC5bKgdiVlT9GWJ9aTegUNgS5wrkErqKGekiRE6iOo/52q9y7hdSqABM9RM4iB1gyPtRrjHe5wyYGlV1BFASJEXCxX5vLq9onuDlrLOGZV0zPU4+hHrihK1aK4cXs1loBBYuosFLml1EAlGxMAScac+lFrCW04twBNu4FcAqPK0Qdxggqcx1rIWlWfMV7HH06j+4rTXUUvbwTDRnBmNS4H/h+dS9aNVGzROUHVc9iBO3t6VVvMhBVimkgg+YbHGJMdqoL1BUsJ6f1j++9OaxIKEDTBXfMHB82/WkmVwBfB3Si+GzqfCJ0EMGGkNqHykwPM5+lXn44LcJR1Idg2mRkQMYXyntvQ7lloAXFa0Ee2+jUNUOhOkHO8g/pRFOGgaRMAEAx+k9ParbSZKjYznGjXbuowBg23wflPy/hjBn8qyfOrhS/cAsXXBYvKrjzecgY6FiPpWlRmuW3RXKPBAPYjaN+oH3q7b8UgGCTAmJievTvVY5JdkTjfRhr/D6UjWQDAzER16dqbeViADcKoxAyACRuemMD3qa9bVYKOgAAJyIJnygic7N60p4pbtwMGVNGdJI0ycRPUQDn+LvSRJNw3C3Cp0uyL0wJPqcYH5+1ILDXGWGbxFksDGIjsMgkrB/mKIHm9vQFQjUejEALG+o9p7TNQ8v49Uu3GDLcYqq6j/2xuTtuMjyjOMnrQrDRPw/Cm44Cs50AswnKt8oEfVq7jOCHjC27lQq6iWfrMwDMA+Wady3hrNx7j3HUnUACVLTAEwNhksMdqpK9jXdlS0mLahDpj5NwPKZUnP0zIoXYmNRLWp9d0qFyDqIZjkyPXzbbn71pPgqwDbuvDQW0ypaIXcHSeqld8VlrXFWxbZWQtcOQ8HSs+cQYEMN4G+M9t3ySwlrhUDBZI1EnRJJPdsj6U59BEfywAXbjSmoBLY1Fp1HzGAciQ4HvUXNeIDWSpMIjyxHytHlUAEknf06UJ4Xif2puKclyTAGM4HmUjouROKt8z4t2GjwwoOS2oHzHE6fr2FT8i40aLG7TAo4YKknSA0mdJnaSPsKxYDA6hI6e53P616Dx6BbFxtWdDwPL1BXee1YdLQjV1zWmB2myfI00hbCFokSWYAH1lPy8wmidvgLRRWuO3iMY0rAEkk/uyAZ/OqXB8K823DAefAJwSJOfQlIrS8q5Qbtu3cNzToRJGmSTEk4Ijf8AOtHLjsxSsntc4Pgry9I0l7VtoDagGdNXnIGdLDMb07/EseHcsW+qI/4yQVJUKQrMdGFIgQMYxFC7CMOKbQAXF+2qg7ErctgTnAOjvWq5zyxeKuW/EjyqMiSxZmYMXlQdUpEACIgDAAzckmmXTaPNwuDHmMHal4hCAdR09pmT7AZ+tQnh5Jj1pbtkgH8RzjP8s1uZm95xbVuWzpClGskDSJcaLYZiw3AmIBxqk7rGLRPMpIif5/2K1+sXOXuGufJ+0AOmAVVQoURMmCD/AAiPxA1c5w1sIUtIyHUrXGGsqCGBCqflgmW2HTEkgYqXHRrVma5hcY8BaQj5L5AM/vI5OIx8oz7UI5UdN4e6/wC3+horzbima2yMAP2isSFAkxEzGKD2PLeXtA/3f8VSX4sXUkazmXlHzYyNycHfEdqE3nY2wCSJDZJ36x6HET6R1o1zEk2xLTnAA6ZO80G4hCPKcABsj3I3+lc8DonTGrbuec/tfMisD4ZEsNhlfbIpLgXVLF/mnOJEQZ22bNQrYSTgnBiT1qK5bAJEbbe39mroyr9kt8goVBMlViXEalO8auoiiXCXhgI8PBI3Ok4YEkjSSG95G9CE9qt8K0MDtn9cfzp0I0Vsa7bW2CsGUNCghTOTAO3mzWZ/y6h9QVg20SI7bBe/51qeGuAiJEDGe0mB+n3oDxG7Hqpz79fzqYqrRpJppMiTSTsJ/P8A9VpHnwPEAIKBW67KQT/OswzGTp+xj+fpROzzMLw9y3ckEowXSAckYDeYacx3605QYlNGps2Gjqeu0eo9xtmmXjEkyTnc1T5V8TcIqJrS6zBQCMBSfQgjHvWl4T4itsv7Kxdz0W3P5g5rP45FPIgE+jQ0vbQlTGpoO2PLvP0q5wD8PdCn/MAkqJVEaRIkgkDcUbtcZbYgaGUn961cX8yoE1fDf3/7quKI5sA2Phvhw5fS5JMljcuL74RhV7/pFn91v/yXP/6q+zVVfiGBiB/9woF2eRqbYdyGLAsQGAJEDG6iN5P1plvSNd9ZLaiI0tlRiMDG0g9CfcEnw3C3AuhOHvuQv/03gmMkmMSakuWmt2lt2+Hutd06flxIEZzAzvTEUU4wXNg6J1IRtTdwIHlHSd+0b1Pyu6i25CNB1PhYEEkjf+GKYniC2baWHBC6QWa0oGIBJa5V4ajaNsoiAoQNV22PL8uIcz77YptCQvB8Tbt2NRt3S2kuSAoEmXb5iDGTQU3yvDm2bcap/aHTDYEdT5pO+0DHoZ47iFa29tvCBfGLlxoXqAUtETEjehvGanQC5dUKmkwqtqYzIYwQJGOo+8UL9gxtx7gtpba2FtgyD1cAiJEeUxk9TvjIravxGmzIIOlVACzk7DZ+0dKyv+ZRwG8S49xFx+zVVDbapFwwczPeNqK8HfuBBLPnJknc560pFRRNwLqqDWmYALat8RGO/f8A91QvENcgAwDjfpkD7mr/AA9tSYJnuP7iqZtEEMSq5J+bI3PSTWNUzqT0RfEI08M2Gk6Fk7fMpMY9DWUBAQDrE0e+JroNlR5pZwcmf3j29qzxHSB/OurCqiced3It2APDOPNEA/8AyY77jLflWp5U5RSkCCqb+hjbtisvYsMzIgBJKrpG5JkGABucmt5w1/iraBV4SwsbvcjUdznU477RSmrQomV5UQ1/UWCA351EwF87tJPT5aJ8249VuErdDeVCWt3CQzjUMydRAEDaO/SqHNOWMiRrtMzXJKI4LAkOxJjAGT16ih6cBcgQjNOBpBbufwg9qfFNbYW1oHKCPt/KlumZH50Xt8g4pxC2Lp/+Mf64ovw3wLxj5NsW/wDyZT94Nac4kcWBrPNQvD3LGljrBEyAASNJMQZGx6daiPHNFwAf9xlYmTgg6pHfNa+z8CMFHi3bSNGYYn7SVpU+F+CB0vxRYjBCJJn6aqi4lfkYXieIdwZjMTAOY23NVTOu2T7fmv8AWvSzyjlqgSl9wOpDKPqQFmn3eJ4JbYtLwYdFOpVZzpDbagpO+Tmjmukh8X22ZnjXHhrv0JEf2ZqrxramnMaevYmf50f49rlxdNvgmUbiA7R0HTaKDNyfirnnFm4AJX5CO/Q5J9qiMUi5SsoW0AZSfY/z/Km8eAHORnP8/wBKt8Rym5bANy269PNIBPrj8qrXrWozpGwHX0E564FWkQ5FXV2G8/1pq3vSauW7bCADEbenT6U42ZaSZJ33yfUmnQuRMvEMChgxB1ATkYI+taLklmxceUUG7pyrK8jocN5T+dZx1AUAkgdDMntvAg71HZvMDAuMomcFt/YH9KmUbKjKjfXuDuE/JZgHGq0Nu2DFVl4W5bMnhLTjEFBb9d9azER171nuA5heVgT4t0dpuH19v1o/w/xOvytw91ANzDGD2grP6Vk1JFJpk68abfmt8CFbppCLq9JDY+tTn4nujynhHDxMSsA9AT2PfPtUnC8wS6fIp+qMI+9Tm2s7R9oqeTXZXFFRvjBVw1m6I6hfL/xUifEwcL4do+YGCdWCNwyr16gyfpVm1YWcgjtiatWraL8q59d/6CnYUgen+ZumdT2x0KaR9CHVsfQGnjkI/FdMnJk5/wBNEWf3/So5FGws8gs2XgtrYn/yOffvVwcEotzcmegn/ilrq2ZBGnCL1mIPfP8AeKmdE0jVkxPv9jXV1TIqJTtLqYD1oi1mARE7ZP8AyK6uqWUXTbISJxjHr9/WrfD3MEeXeMke09a6uqChUMyAdQ9J/oJqF7bsBotT0wrGT/WurqlLYOTos8y+FeJvhMJaVR+OR9QInGar2vgRVOq7xtuOoVQJ9MNS11bJ0jF7ew3wXCcDw48rhmiC5Qu5HaWwB6AAVP8A9Y4dMqLh9hbT/QJrq6gZWvfENuZFhSehdmY/nFInxPefFqyDG+i2SQf/ANq6uoa0JscnH8yufKrKPUaP9RWuHK+Nuf8AcuBR/ExP+kH9a6uqeTGTW/hSf+5fn0UfoWOftRPgeS27aBNbsBP7vWT29a6upWBYHL7QGkqW/wDIT+W32FTqiKIVFEdAFx322rq6kMXxCf6bn3iaTUYyCft+ddXUAMv8Mj4ZFYfxCSR2Eis9zbkmpD4dtRpyAFz33LaQK6up20Noxb8HcZoS27E4OMg+wNE+A+HLjKQ9t1YwVYAGBBBDAxGYzJ9q6uq3J0QooIcB8OvbeTdtSNwRrk+o0jBjBnpRbl3EW7gZXtLqU6TABHvkbGkrqluyki1/062xkWkAEwfDQGPoBVqzZRQFy8CBORHua6uqCmOWyBIRVQHcKqifeK5Lar0z+f3rq6qEKz1GXPtXV1MkT60ketdXUwP/2Q=="));
            events_items.add(new EventItem("News 5", "News 5 discreption", "2022 / 05 / 22", ""));
//        }, StaticVar.data_load_delay);
    }
}